import java.io.IOException;
/**
 * @Author: Wenke
 * @Date: 2021/2/25 16:12
 */
public class Generate {
    public static void main(String args[]) throws IOException, customException {
        Boolean help=false;
        if (args.length!=0){
            for (String str : args) {
                if ("-h".equals(str) || "--help".equals(str)|| "-help".equals(str)){
                    help=true;
                    break;
                }
            }
        }else {
            help=true;
        }
        if (help) {
            System.out.println("Usage: java -jar [thisjar] [Options]" +
                    "\n  e.g. | java -jar [thisjar] [-d=*] [-rg/rp=*] [-o=*]" +
                    "\n  [-t=node] [-nodeNum=*] [-ref=*] [-ss=*] [-sd=*] [-overlap=*] [-angle=*] [-label=*] [-cross=*]" +
                    "\n  [-t=nodeFix] [-ss=*] [-sd=*] [-coordinate=*]" +
                    "\n  [-t=bezier] [-bezierNum=*] [-ss=*] [-rss=*] [-sd=*] [-rsd=*] [-control=*] [-label=*] [-offset=*]"+
                    "\n  ..."+
                    "\nOptions:"+
                    "\n    -h, --help"+
                    "\n        Print this help"+
                    "\n    -angle=[Number vector, only for -t=node]"+
                    "\n        The vector of the new cluster’s counterclockwise rotation angle," +
                    "\n        relative to the reference UC in each dimension"+
                    "\n        Default: 360×random(0,1)"+
                    "\n    -bezierNum=[Positive integer, only for -t=bezier]"+
                    "\n        The number of BCs to add to the current layer"+
                    "\n        Default: 1"+
                    "\n    -control=[Number vector, only for -t=bezier]"+
                    "\n        The control point of the Bezier curve"+
                    "\n        Default: 30×max(1,SD)×random(0,1)"+
                    "\n    -coordinate=[Number vector, only for -t=nodeFix]"+
                    "\n        The centre coordinates of the new cluster"+
                    "\n    -cross=[0/1]"+
                    "\n        Whether clusters with conflicting parameters are shown"+
                    "\n        Default: 0, not displayed"+
                    "\n    -d=[Positive integer]"+
                    "\n        Dimension"+
                    "\n        Default: 2"+
                    "\n    -label=[Integer] "+
                    "\n        Specify a label for the new cluster. Default is increment"+
                    "\n    -nodeNum=[Positive integer, only for -t=node]"+
                    "\n        The number of UCs to add to the current layer"+
                    "\n        Default: 1"+
                    "\n    -o=[FilePath]"+
                    "\n        Output. Default is the current directory"+
                    "\n    -offset=[Number vector, only for -t=bezier]"+
                    "\n        The translation of the new BC in each dimension"+
                    "\n        Default is 0"+
                    "\n    -overlap=[Number, only for -t=node]"+
                    "\n        The overlap between the new cluster and the reference cluster."+
                    "\n        It is also the largest overlap between the new cluster and other clusters."+
                    "\n        Default: [0.7×random(0,1), -1×random(0,1)]"+
                    "\n    -ref=[Positive integer, only for -t=node]"+
                    "\n        The reference UC for the new cluster. All UCs are"+
                    "\n        numbered sequentially from 0-n. Each BC consists of multiple (200) UCs."+
                    "\n        Default: round(random(0,1)×UC_Num)"+
                    "\n    -rg=[FilePath]"+
                    "\n        Regenerate according to the original parameters in a parameter configure file"+
                    "\n    -rp=[FilePath]"+
                    "\n        Reproduce according to a parameter configure file"+
                    "\n    -rsd=[Number, only for -t=bezier]"+
                    "\n        The ratio of the ending SD to the starting SD"+
                    "\n        Default: [5×random(0,1), -5×random(0,1)]"+
                    "\n    -rss=[Number, only for -t=bezier]"+
                    "\n        The ratio of the ending sample size to the starting sample size"+
                    "\n        Default: [10×random(0,1), -10×random(0,1)]"+
                    "\n    -sd=[Positive number]"+
                    "\n        The standard deviation of normal distribution"+
                    "\n        Default: 2.2-2.1×random(0,1))"+
                    "\n    -ss=[Positive integer]"+
                    "\n        The sample size for each cluster to add to the current layer"+
                    "\n        Default: 300×(1+SD×random(0,1))"+
                    "\n    -t=[node/nodeFix/bezier]"+
                    "\n        Three generation models."+
                    "\n        node = add UCs using relative positioning"+
                    "\n        nodeFix = add UCs using absolute positioning"+
                    "\n        bezier = add BCs");
        } else {
            System.out.println("====================");
            String[] results = ClusteringDataGenerator.run(args, "").split(";");
            String[] results2 = results[0].split(",");
            System.out.println("Dimension: "+results2[0]);
            System.out.println("Total sample size: "+results2[1]);
            if (" ".equals(results)){
                System.out.println("Warning: Parameters conflict for cluster "+results[1]);
            }
            System.out.println("====================");
        }

    }
}
