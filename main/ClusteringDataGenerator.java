import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.CombinatoricsUtils;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
/**
 * @Author: Wenke
 * @Date: 2020/12/14 15:52
 */
class ClusteringDataGenerator {
    private static float[] all_center = new float[100000];
    private static float[] all_radius = new float[1000000];
    private static String[] all_type = new String[1000000];
    private static int[] all_sampleNum = new int[1000000];
    private static float[] all_sd = new float[1000000];
    private static String[] all_coordinate = new String[1000000];
    private static float[] all_sampleM = new float[1000000];
    private static float[] all_sdM = new float[1000000];
    private static String[] all_control = new String[1000000];
    private static String[] all_offset = new String[1000000];
    private static int[] all_label = new int[1000000];
    private static int clusterNum = 0;
    private static int clusterID = 0;
    private static float sdFold = 3f;
    private static String output;
    private static BufferedWriter bw;
    private static int[] resultNum = new int[2];
    private static String conflict = " ";
    private static DecimalFormat df = new DecimalFormat("0.000");
    static String run(String[] args, String path) throws customException, IOException {
        all_center = new float[1000000];
        all_radius = new float[1000000];
        all_type = new String[1000000];
        all_sampleNum = new int[1000000];
        all_sd = new float[1000000];
        all_coordinate = new String[1000000];
        all_sampleM = new float[1000000];
        all_sdM = new float[1000000];
        all_control = new String[1000000];
        all_offset = new String[1000000];
        all_label = new int[1000000];
        clusterNum = 0;
        clusterID = 0;
        resultNum = new int[2];
        conflict = " ";
        int sampleNum = 0;
        int dimension = 2;
        int dimensionAlready = 0;
        float sd = 9999999;
        float rsd = 9999999;
        float[] angle = {};
        float overlap = 9999999;
        int ref = -1;
        float[] coordinate = {};
        float rss = 9999999;
        float[] control = {};
        float[] offset = {};
        int customLabel = 9999999;
        int cross = 1;
        String[] config = {};
        String[] strs;
        String[] strs2;
        String str = "";
        String str2 = "";
        float[] fls;
        int nodeNum = 1;
        int bezierNum = 1;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_z");
        for (int i = 0; i < args.length; i++) {
            BufferedReader br;
            if (args[i].startsWith("-o=")) {
                output = args[i].substring(args[i].indexOf("=") + 1);
            } else if (args[i].startsWith("-d=") && dimensionAlready == 0) {
                dimension = Integer.valueOf(args[i].substring(args[i].indexOf("=") + 1));
                dimensionAlready = 1;
            } else if (args[i].startsWith("-rg=")) {
                br = new BufferedReader(new FileReader(path + args[i].substring(args[i].indexOf("=") + 1)));
                br.readLine();
                str = br.readLine() + " ";
                while ((str2 = br.readLine()) != null && !str2.startsWith("#recurrent")) {
                    str += str2 + " ";
                }
                strs = StringUtils.split(str, " ");
                strs2 = new String[strs.length + args.length - 1];
                System.arraycopy(args, 0, strs2, 0, i);
                System.arraycopy(strs, 0, strs2, i, strs.length);
                System.arraycopy(args, i + 1, strs2, i + strs.length, args.length - i - 1);
                args = new String[strs2.length];
                System.arraycopy(strs2, 0, args, 0, strs2.length);
                br.close();
                i--;
            } else if (args[i].startsWith("-rp=")) {
                br = new BufferedReader(new FileReader(path + args[i].substring(args[i].indexOf("=") + 1)));
                while ((str = br.readLine()) != null) {
                    if (str.startsWith("#recurrent")) {
                        str = br.readLine() + " ";
                        while ((str2 = br.readLine()) != null) {
                            str += str2 + " ";
                        }
                        strs = StringUtils.split(str, " ");
                        strs2 = new String[strs.length + args.length - 1];
                        System.arraycopy(args, 0, strs2, 0, i);
                        System.arraycopy(strs, 0, strs2, i, strs.length);
                        System.arraycopy(args, i + 1, strs2, i + strs.length, args.length - i - 1);
                        args = new String[strs2.length];
                        System.arraycopy(strs2, 0, args, 0, strs2.length);
                    }
                }
                br.close();
                i--;
            }
        }
        if (output == null) {
            output = "AC_" + formatter.format(System.currentTimeMillis()) + ".txt";
        } else if (new File(output).isDirectory()) {
            output += "/AC_" + formatter.format(System.currentTimeMillis()) + ".txt";
        }
        output = output.replace("//", "/");
        File file = new File(output);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println("The output directory does not exist and will be created automatically: " + file.getParentFile());
            } else {
                throw new customException("The output directory can not be created.");
            }
        }
        bw = new BufferedWriter(new FileWriter(output));
        bw.write("dimension_1");
        for (int j = 1; j < dimension; j++) {
            bw.write("\tdimension_" + (j + 1));
        }
        bw.write("\tlabels\n");
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-t=")) {
                switch (args[i].substring(args[i].indexOf("=") + 1)) {
                    case "node":
                        do {
                            if (i < args.length - 1 && !args[i + 1].startsWith("-t=")) {
                                i++;
                                strs = args[i].split("=");
                                switch (strs[0]) {
                                    case "-ss":
                                        sampleNum = Integer.valueOf(strs[1]);
                                        break;
                                    case "-nodeNum":
                                        nodeNum = Integer.valueOf(strs[1]);
                                        break;
                                    case "-sd":
                                        sd = Float.valueOf(strs[1]);
                                        break;
                                    case "-angle":
                                        strs = strs[1].split(",");
                                        fls = new float[strs.length];
                                        for (int j = 0; j < strs.length; j++) {
                                            fls[j] = Float.valueOf(strs[j]);
                                        }
                                        angle = fls;
                                        break;
                                    case "-overlap":
                                        overlap = Float.valueOf(strs[1]);
                                        break;
                                    case "-ref":
                                        ref = Integer.valueOf(strs[1]);
                                        break;
                                    case "-cross":
                                        cross = Integer.valueOf(strs[1]);
                                        break;
                                    case "-label":
                                        customLabel = Integer.valueOf(strs[1]);
                                        break;
                                    case "-o":
                                        break;
                                    case "-d":
                                        break;
                                    case "-rg":
                                        break;
                                    case "-rp":
                                        break;
                                    default:
                                        System.out.println("Warning | Unknown or misplaced parameter: " + strs[0]);
                                }
                            }
                        } while (i < args.length - 1 && !args[i + 1].startsWith("-t="));
                        if (nodeNum > 1) {
                            angle = new float[0];
                            for (int j = 0; j < nodeNum; j++) {
                                makeGd(sampleNum, dimension, sd, angle, overlap, -1, cross, customLabel);
                            }
                        } else {
                            makeGd(sampleNum, dimension, sd, angle, overlap, ref, cross, customLabel);
                        }
                        sampleNum = 0;
                        nodeNum = 1;
                        sd = 9999999;
                        angle = new float[0];
                        overlap = 9999999;
                        ref = -1;
                        cross = 1;
                        customLabel = 9999999;
                        break;
                    case "nodeFix":
                        do {
                            if (i < args.length - 1 && !args[i + 1].startsWith("-t=")) {
                                i++;
                                strs = args[i].split("=");
                                switch (strs[0]) {
                                    case "-ss":
                                        sampleNum = Integer.valueOf(strs[1]);
                                        break;
                                    case "-dimension":
                                        dimension = Integer.valueOf(strs[1]);
                                        break;
                                    case "-sd":
                                        sd = Float.valueOf(strs[1]);
                                        break;
                                    case "-coordinate":
                                        strs = strs[1].split(",");
                                        coordinate = new float[strs.length];
                                        for (int j = 0; j < strs.length; j++) {
                                            coordinate[j] = Float.valueOf(strs[j]);
                                        }
                                        break;
                                    case "-label":
                                        customLabel = Integer.valueOf(strs[1]);
                                        break;
                                    case "-o":
                                        break;
                                    case "-d":
                                        break;
                                    case "-rg":
                                        break;
                                    case "-rp":
                                        break;
                                    default:
                                        System.out.println("Warning | Unknown or misplaced parameter: " + strs[0]);
                                }
                            }
                        } while (i < args.length - 1 && !args[i + 1].startsWith("-t="));
                        makeGdFix(sampleNum, dimension, sd, coordinate, customLabel);
                        sampleNum = 0;
                        sd = 9999999;
                        customLabel = 9999999;
                        coordinate = new float[0];
                        break;
                    case "bezier":
                        do {
                            if (i < args.length - 1 && !args[i + 1].startsWith("-t=")) {
                                i++;
                                strs = args[i].split("=");
                                switch (strs[0]) {
                                    case "-ss":
                                        sampleNum = Integer.valueOf(strs[1]);
                                        break;
                                    case "-bezierNum":
                                        bezierNum = Integer.valueOf(strs[1]);
                                        break;
                                    case "-rss":
                                        rss = Float.valueOf(strs[1]);
                                        break;
                                    case "-sd":
                                        sd = Float.valueOf(strs[1]);
                                        break;
                                    case "-rsd":
                                        rsd = Float.valueOf(strs[1]);
                                        break;
                                    case "-control":
                                        strs = strs[1].split(",");
                                        fls = new float[strs.length];
                                        for (int j = 0; j < strs.length; j++) {
                                            fls[j] = Float.valueOf(strs[j]);
                                        }
                                        control = fls;
                                        break;
                                    case "-offset":
                                        strs = strs[1].split(",");
                                        fls = new float[strs.length];
                                        for (int j = 0; j < strs.length; j++) {
                                            fls[j] = Float.valueOf(strs[j]);
                                        }
                                        offset = fls;
                                        break;
                                    case "-label":
                                        customLabel = Integer.valueOf(strs[1]);
                                        break;
                                    case "-o":
                                        break;
                                    case "-d":
                                        break;
                                    case "-rg":
                                        break;
                                    case "-rp":
                                        break;
                                    default:
                                        System.out.println("Warning | Unknown or misplaced parameter: " + strs[0]);
                                }
                            }
                        } while (i < args.length - 1 && !args[i + 1].startsWith("-t="));
                        if (bezierNum > 1) {
                            control = new float[0];
                            offset = new float[0];
                            for (int j = 0; j < bezierNum; j++) {
                                makeBezier(sampleNum, rss, sd, rsd, dimension, control, offset, customLabel);
                            }
                        } else {
                            makeBezier(sampleNum, rss, sd, rsd, dimension, control, offset, customLabel);
                        }
                        sampleNum = 0;
                        bezierNum = 1;
                        rss = 9999999;
                        sd = 9999999;
                        rsd = 9999999;
                        customLabel = 9999999;
                        control = new float[0];
                        break;
                    default:
                        break;
                }
            }
        }
        resultNum[0] = dimension;
        System.out.println("Done.");
        dimensionAlready = 0;
        bw.flush();
        bw.close();
        bw = new BufferedWriter(new FileWriter(output + ".config"));
        bw.write("#parameters\n-d=" + dimension);
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("-t=")) {
                bw.write("\n");
            }
            if (!args[i].startsWith("-o=") && !args[i].startsWith("-d=") && !args[i].startsWith("-rg=") && !args[i].startsWith("-rp=") && !args[i].startsWith("-d=")) {
                bw.write(args[i] + " ");
            }
        }
        if (!args[args.length - 1].startsWith("-o=") && !args[args.length - 1].startsWith("-d=") && !args[args.length - 1].startsWith("-rg=") && !args[args.length - 1].startsWith("-rp=") && !args[args.length - 1].startsWith("-d=")) {
            bw.write(args[args.length - 1]);
        }
        bw.write("\n#recurrent\n");
        bw.write("-d=" + dimension);
        for (int i = 0; i < clusterID; i++) {
            if (all_type[i] == "bezier") {
                bw.write("\n-t=bezier -ss=" + all_sampleNum[i]);
                if (all_sampleM[i] != 9999999) {
                    bw.write(" -rss=" + all_sampleM[i]);
                }
                if (all_sd[i] != 9999999) {
                    bw.write(" -sd=" + all_sd[i]);
                }
                if (all_sdM[i] != 9999999) {
                    bw.write(" -rsd=" + all_sdM[i]);
                }
                if (all_control[i] != null) {
                    bw.write(" -control=" + all_control[i]);
                }
                if (all_offset[i] != null) {
                    bw.write(" -offset=" + all_offset[i]);
                }
                if (all_label[i] != 9999999) {
                    bw.write(" -label=" + all_label[i]);
                }
            } else {
                bw.write("\n-t=nodeFix -ss=" + all_sampleNum[i]);
                if (all_sd[i] != 0) {
                    bw.write(" -sd=" + all_sd[i]);
                }
                if (all_coordinate[i] != null) {
                    bw.write(" -coordinate=" + all_coordinate[i]);
                }
                if (all_label[i] != 9999999) {
                    bw.write(" -label=" + all_label[i]);
                }
            }
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        System.out.println("Output-coordinate: " + output);
        System.out.println("Output-config: " + output + ".config");
        return resultNum[0] + "," + resultNum[1] + ";" + conflict;
    }
    private static void makeGd(int sampleNum, int dimension, float sd, float[] angle, float overlap, int ref, int cross, int label) throws customException, IOException {
        int magicSampleNum = 0;
        int magicDimension = 0;
        int magicSd = 9999999;
        int magicOverlap = 9999999;
        int magicRef = -1;
        int magicLabel = 9999999;
        int customSampleNum = sampleNum;
        float customSd = sd;
        float[] customAngle = angle;
        float customOverlap = overlap;
        int customRef = ref;
        boolean check;
        NormalDistribution norm;
        float distance = 0;
        int times = 0;
        float ns = 0;
        float nsdistance = 0;
        if (dimension == magicDimension) {
            dimension = 2;
        }
        do {
            times++;
            if ((int) customSd == magicSd) {
                sd = (float) (1 + 10 * Math.random());
            }
            if (customSampleNum == magicSampleNum) {
                sampleNum = (int) (Math.random() * sd * 300) + 300;
            }
            if (customAngle.length != dimension) {
                angle = new float[dimension];
                angle[0] = 90;
                for (int i = 1; i < dimension; i++) {
                    angle[i] = (float) (Math.random() * 360);
                }
            }
            if ((int) customOverlap == magicOverlap) {
                if (Math.random() >= 0.5) {
                    overlap = (float) (0.7 * Math.random());
                } else {
                    overlap = (float) (-1 * Math.random());
                }
            }
            if (customRef == magicRef) {
                ref = (int) Math.round(Math.random() * (clusterNum - 1));
            }
            if (clusterNum == 0) {
                for (int i = 0; i < dimension; i++) {
                    all_center[clusterNum * dimension + i] = 0;
                }
            } else {
                distance = Math.max(all_radius[ref] + sd * sdFold - overlap * Math.min(all_radius[ref], sd * sdFold), 0);
                ns = (float) (distance * Math.sin(Math.toRadians(angle[dimension - 1])));
                nsdistance = (float) (distance * Math.cos(Math.toRadians(angle[dimension - 1])));
                all_center[clusterNum * dimension + dimension - 1] = all_center[ref * dimension + dimension - 1] + ns;
                for (int i = dimension - 2; i > 0; i--) {
                    ns = (float) (nsdistance * Math.sin(Math.toRadians(angle[i])));
                    nsdistance = (float) (nsdistance * Math.cos(Math.toRadians(angle[i])));
                    all_center[clusterNum * dimension + i] = all_center[ref * dimension + i] + ns;
                }
                all_center[clusterNum * dimension + 0] = all_center[ref * dimension + 0] + nsdistance;
            }
            all_radius[clusterNum] = (float) (sd * sdFold);
            check = checkOverlap(dimension, overlap, sd, clusterNum - 1, clusterNum);
        } while (!check && times < Math.max(10, 3 * clusterNum));
        if (cross == 1 || check) {
            norm = new NormalDistribution(0, sd);
            for (int i = 0; i < sampleNum; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (output.length() > 1) {
                        bw.write(df.format(norm.sample() + all_center[clusterNum * dimension + j]) + "\t");
                    }
                }
                if (output.length() > 1) {
                    if (label != magicLabel) {
                        bw.write(label + "\n");
                    } else {
                        bw.write(clusterID + "\n");
                    }
                }
            }
            resultNum[1] = resultNum[1] + sampleNum;
        } else {
            if (cross == 0) {
                System.out.println("##### Warning: Parameters conflict for cluster " + clusterNum);
                if (!conflict.equals(" ")) {
                    conflict += ", " + clusterID;
                } else {
                    conflict += clusterID;
                }
            }
        }
        all_type[clusterID] = "nodeFix";
        all_sampleNum[clusterID] = sampleNum;
        all_sd[clusterID] = sd;
        if (label != magicLabel) {
            all_label[clusterID] = label;
        } else {
            all_label[clusterID] = clusterID;
        }
        StringBuilder str = new StringBuilder();
        str.append(all_center[clusterNum * dimension]);
        for (int i = 1; i < dimension; i++) {
            str.append("," + all_center[clusterNum * dimension + i]);
        }
        all_coordinate[clusterID] = str.toString();
        clusterNum++;
        clusterID++;
    }
    private static void makeGdFix(int sampleNum, int dimension, float sd, float[] coordinate, int label) throws customException, IOException {
        int magicSampleNum = 0;
        int magicDimension = 0;
        int magicSd = 9999999;
        int magicLabel = 9999999;
        int customSampleNum = sampleNum;
        float customSd = sd;
        NormalDistribution norm;
        if (dimension == magicDimension) {
            dimension = 2;
        }
        if ((int) customSd == magicSd) {
            sd = (float) (1 + 10 * Math.random());
        }
        if (customSampleNum == magicSampleNum) {
            sampleNum = (int) (Math.random() * sd * 300) + 300;
        }
        for (int i = 0; i < dimension; i++) {
            all_center[clusterNum * dimension + i] = coordinate[i];
        }
        all_radius[clusterNum] = sd * sdFold;
        norm = new NormalDistribution(0, sd);
        for (int i = 0; i < sampleNum; i++) {
            for (int j = 0; j < dimension; j++) {
                if (output.length() > 1) {
                    bw.write(df.format(norm.sample() + all_center[clusterNum * dimension + j]) + "\t");
                }
            }
            if (output.length() > 1) {
                if (label != magicLabel) {
                    bw.write(label + "\n");
                } else {
                    bw.write(clusterID + "\n");
                }
            }
        }
        resultNum[1] = resultNum[1] + sampleNum;
        all_type[clusterID] = "nodeFix";
        all_sampleNum[clusterID] = sampleNum;
        all_sd[clusterID] = sd;
        if (label != magicLabel) {
            all_label[clusterID] = label;
        } else {
            all_label[clusterID] = clusterID;
        }
        StringBuilder str = new StringBuilder();
        str.append(all_center[clusterNum * dimension]);
        for (int i = 1; i < dimension; i++) {
            str.append("," + all_center[clusterNum * dimension + i]);
        }
        all_coordinate[clusterID] = str.toString();
        clusterNum++;
        clusterID++;
    }
    private static boolean checkOverlap(int dimension, float checkOverlap, float checkSD, int refClusterNum, int currentClusterNum) {
        float sum = 0;
        float checkDistance;
        for (int i = 0; i <= refClusterNum; i++) {
            sum = 0;
            for (int j = 0; j < dimension; j++) {
                sum += (float) Math.pow(all_center[i * dimension + j] - all_center[currentClusterNum * dimension + j], 2);
            }
            sum = (float) Math.sqrt(sum);
            checkDistance = Math.max(all_radius[i] + checkSD * sdFold - checkOverlap * Math.min(all_radius[i], checkSD * sdFold), 0);
            if (checkDistance > sum + 0.00001) {
                return false;
            }
        }
        return true;
    }
    private static void makeBezier(int sampleNum, float sampleM, float sd, float rsd, int dimension, float[] control, float[] offset, int label) throws customException, IOException {
        int magicSd = 9999999;
        int magicSdM = 9999999;
        int magicSm = 9999999;
        int magicLabel = 9999999;
        int len = 200;
        double sample0;
        int preCluterNum = clusterNum;
        boolean check = true;
        double sampledd;
        double sampleleft = 0;
        int sampletmp;
        int preresultNum = resultNum[1];
        if (sd == magicSd) {
            sd = (float) (2.2 - 2.1 * Math.random());
        }
        if (sampleNum == 0) {
            sampleNum = 300 + (int) (Math.random() * 300 * sd);
        }
        if (sampleM != magicSm) {
            if (sampleM < 0) {
                sample0 = 2.0 * sampleNum * (1.0 - sampleM) / len / (2.0 - sampleM);
                sampledd = 2.0 * sampleNum * sampleM / len / (len - 1) / (2.0 - sampleM);
            } else {
                sample0 = 2.0 * sampleNum / len / (2.0 + sampleM);
                sampledd = 2.0 * sampleNum * sampleM / len / (len - 1) / (2.0 + sampleM);
            }
        } else {
            if (Math.random() < 0.3) {
                sampleM = 0;
            } else if (Math.random() < 0.5) {
                sampleM = (float) Math.random() * 10;
            } else {
                sampleM = (float) -Math.random() * 10;
            }
            if (sampleM < 0) {
                sample0 = (double) 2.0 * sampleNum * (1.0 - sampleM) / len / (2.0 - sampleM);
                sampledd = (double) 2.0 * sampleNum * sampleM / len / (len - 1) / (2.0 - sampleM);
            } else {
                sample0 = (double) 2.0 * sampleNum / len / (2.0 + sampleM);
                sampledd = (double) 2.0 * sampleNum * sampleM / len / (len - 1) / (2.0 + sampleM);
            }
        }
        float sd_dd;
        if (rsd != magicSdM) {
            if (rsd < 0) {
                sd_dd = (float) sd * rsd / (1 - rsd) / len;
            } else {
                sd_dd = (float) sd * rsd / len;
            }
        } else {
            if (Math.random() < 0.3) {
                rsd = 0;
            } else if (Math.random() < 0.5) {
                rsd = (float) Math.random() * 5;
            } else {
                rsd = (float) -Math.random() * 5;
            }
            if (rsd < 0) {
                sd_dd = (float) sd * rsd / (1 - rsd) / len;
            } else {
                sd_dd = (float) sd * rsd / len;
            }
        }
        if (dimension == 0) {
            dimension = 2;
        }
        int n = control.length / dimension;
        int nPrimary = n;
        double sample0Primary = sample0;
        float sdPrimary = sd;
        float sd_ddPrimary = sd_dd;
        if (n == 0) {
            n = 3;
            control = new float[n * dimension];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < dimension; j++) {
                    if (Math.random() >= 0.5) {
                        control[i * dimension + j] = (float) (Math.random() * 30 * Math.max(sd, 1));
                    } else {
                        control[i * dimension + j] = (float) (-Math.random() * 30 * Math.max(sd, 1));
                    }
                }
            }
            offset = new float[0];
        }
        NormalDistribution norm;
        float[] x = new float[n];
        float[] position = new float[len * dimension];
        int offsetPrimary = offset.length;
        if (offsetPrimary == 0) {
            offset = new float[dimension];
        }
        int time = 0;
        do {
            clusterNum = preCluterNum + 1;
            sample0 = sample0Primary;
            sd = sdPrimary;
            sd_dd = sd_ddPrimary;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < dimension; j++) {
                    for (int k = 0; k < n; k++) {
                        x[k] = control[k * dimension + j];
                    }
                    position[i * dimension + j] = bezier((float) i / len, x) + offset[j];
                    all_center[clusterNum * dimension + j] = position[i * dimension + j];
                    all_radius[clusterNum * dimension + j] = (float) (sd * sdFold);
                }
                if (nPrimary == 0 && offsetPrimary == 0) {
                    check = checkOverlap(dimension, 0, sd, preCluterNum, clusterNum);
                    if (!check) {
                        for (int j = 0; j < dimension; j++) {
                            if (Math.random() > 0.5) {
                                offset[j] = (float) (time / 8 * dimension * sd);
                            } else {
                                offset[j] = -(float) (time / 8 * dimension * sd);
                            }
                        }
                        time++;
                        break;
                    }
                } else {
                    check = true;
                }
                sample0 = sample0 + sampledd;
                sd += sd_dd;
                clusterNum++;
            }
        } while (!check);
        sample0 = sample0Primary;
        sd = sdPrimary;
        sd_dd = sd_ddPrimary;
        sampleleft = 0;
        for (int i = 0; i < len; i++) {
            norm = new NormalDistribution(0, sd);
            sampleleft += (float) (sample0 - Math.floor(sample0));
            if (sampleleft >= 1) {
                sampleleft -= 1;
                sampletmp = (int) Math.ceil(sample0);
            } else if (i == len - 1 && resultNum[1] - preresultNum < sampleNum) {
                sampletmp = (int) Math.ceil(sample0);
            } else {
                sampletmp = (int) Math.max(0, Math.floor(sample0));
            }
            for (int j = 0; j < sampletmp; j++) {
                for (int k = 0; k < dimension; k++) {
                    if (output.length() > 1) {
                        bw.write(df.format(norm.sample() + position[i * dimension + k]) + "\t");
                    }
                }
                if (output.length() > 1) {
                    if (label != magicLabel) {
                        bw.write(label + "\n");
                    } else {
                        bw.write(clusterID + "\n");
                    }
                }
            }
            resultNum[1] = resultNum[1] + sampletmp;
            sample0 = sample0 + sampledd;
            sd += sd_dd;
        }
        all_type[clusterID] = "bezier";
        all_sampleNum[clusterID] = sampleNum;
        all_sampleM[clusterID] = sampleM;
        all_sd[clusterID] = sdPrimary;
        all_sdM[clusterID] = rsd;
        if (label != magicLabel) {
            all_label[clusterID] = label;
        } else {
            all_label[clusterID] = clusterID;
        }
        StringBuilder str = new StringBuilder();
        str.append(control[0]);
        for (int i = 1; i < dimension; i++) {
            str.append("," + control[i]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < dimension; j++) {
                str.append("," + control[i * dimension + j]);
            }
        }
        all_control[clusterID] = str.toString();
        str = new StringBuilder();
        str.append(offset[0]);
        for (int i = 1; i < dimension; i++) {
            str.append("," + offset[i]);
        }
        all_offset[clusterID] = str.toString();
        clusterID++;
    }
    private static float bezier(float t, float p[]) {
        float sum = 0;
        int n = p.length;
        for (int i = 0; i < n; i++) {
            sum += CombinatoricsUtils.binomialCoefficient(n - 1, i) * p[i] * Math.pow(1 - t, n - 1 - i) * Math.pow(t, i);
        }
        return sum;
    }
}
