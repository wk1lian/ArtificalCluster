<img class="logo" src="../../../picture/doc/ac.png"  alt="logo" style="vertical-align:middle;border-style:none;text-align:center">
<b id="head" style="color: #585554; font-size: 24px; font-weight: 900; margin-left: -20px;margin-left:-20px;font-weight:900;font-size:24;text-shadow:0 1px 0 #ccc, 0 2px 0 #c9c9c9, 0 3px 0 #bbb, 0 4px 0 #b9b9b9, 0 5px 0 #aaa, 0 6px 1px rgba(0,0,0,.1), 0 0 5px rgba(0,0,0,.1), 0 1px 3px rgba(0,0,0,.3), 0 3px 5px rgba(0,0,0,.2), 0 5px 10px rgba(0,0,0,.25), 0 10px 10px rgba(0,0,0,.2), 0 20px 20px rgba(0,0,0,.15)"><i>Artificial Cluster</i></b>
<hr style="border-color: #c49f52;margin-top:1rem;margin-bottom:1rem;border-top:1px solid rgba(0,0,0,.1)">

### Artificial Cluster (AC): A data generator for evaluation of clustering

See details: https://doi.org/10.36227/techrxiv.19091330

Official website: http://ac.fwgenetics.org

Clustering has important applications in many fields. However, there are not enough benchmark datasets with rich characteristics for the development and evaluation of clustering algorithms, so the clustering performance cannot be truly evaluated. Neither real data nor manually synthetic data can solve this problem. We propose a new data generator, Artificial Cluster (AC), that can thoroughly customize the cluster characteristics that affect clustering, such as sample size, density, overlap and shape. The randomization of the default parameters enables AC to efficiently generate benchmark datasets with different characteristic combinations that can be used to evaluate the robustness of clustering algorithms. We evaluated nine popular clustering algorithms using an example benchmark dataset generated by AC. From the results, the advantages and disadvantages of these algorithms can be clearly seen. AC is expected to provide sufficient data support for clustering research.

<div class="alert alert-primary" role="alert" style="color:#004085;background-color:#cce5ff;border-color:#b8daff;position:relative;padding:.75rem 1.25rem;margin-bottom:1rem;border:1px solid transparent;border-radius:.25rem">
    <h5 class="py-2" style="padding-bottom:.5rem !important;padding-top:.5rem !important">Hypothesis</h5>
    <p style="font-size: 14px;margin-top:0">1. The clusters in a dataset are always represented in a tree structure. More complex clusters are composed of smaller clusters. The clusters located at leaf nodes cannot be further divided, and we call them unit clusters (UCs).</p>
    <p style="font-size: 14px;margin-top:0">2. The UC��s samples follow an isotropic normal distribution, which means that the samples in each dimension of the UC follow a normal distribution.</p>
</div>
<img src="pic//design.png" class="card-img-top" alt="design">
<a class="badge badge-primary" href="#Arguments" style="font-size: 17px;padding:10px; margin-top: 20px">Check Arguments</a>
<hr style="border-color: #c49f52;margin-top:1rem;margin-bottom:1rem;border-top:1px solid rgba(0,0,0,.1)">

<h4 style="padding: 20px 0"><b>Usage</b>: Three Generation Models</h4>
<div class="card shadow-lg" style="box-shadow:0 1rem 3rem rgba(0,0,0,.175) !important;position:relative;display:flex;flex-direction:column;border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
    <div class="card-header" style="border-radius:calc(.25rem - 1px) calc(.25rem - 1px) 0 0;padding:.75rem 1.25rem;margin-bottom:0;background-color:rgba(0,0,0,.03);border-bottom:1px solid rgba(0,0,0,.125)">
        <b>node</b><i>: Add UCs by relative positioning</i>
    </div>
    <div class="card-body" style="flex:1 1 auto;min-height:1px;padding:1.25rem">
        <p>All parameters:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem;">
            <div>-t=node -nodeNum=1 -ss=1000 -sd=1 -overlap=0 -angle=0,0 -ref=0 -label=0 -cross=0</div>
            <hr style="border-color: #c49f52;margin-top:1rem;margin-bottom:1rem;border-top:1px solid rgba(0,0,0,.1)"">
            <div style="margin-top: 10px;color: #736e63"><i>"-label" is specified only if multiple clusters need to be merged.</i></div>
            <div style="margin-top: 5px;color: #736e63"><i>"-cross" is specified only if the arguments are in conflict.</i></div>
        </div>
        <p>Specify the number of clusters to add on this layer:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <div>-t=node <b style="color: #61080f">-nodeNum=1</b> -ss=1000 -sd=1 -overlap=0 <t style="color: #736e63">-angle=0,0 -ref=0</t></div>
            <hr style="border-color: #c49f52;margin-top:1rem;margin-bottom:1rem;border-top:1px solid rgba(0,0,0,.1)"">
            <div style="margin-top: 10px;color: #736e63"><i>"-angle" and "-ref" are forced to be random When "-nodeNum" is greater than 1.</i></div>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//nodeNum1.png" class="card-img-top" alt="-nodeNum=1">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-nodeNum=1</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//nodeNum5.png" class="card-img-top" alt="-nodeNum=5">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-nodeNum=5</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//nodeNum10.png" class="card-img-top" alt="-nodeNum=10">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-nodeNum=10</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//nodeNum20.png" class="card-img-top" alt="-nodeNum=10">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-nodeNum=20</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//nodeNum30.png" class="card-img-top" alt="-nodeNum=10">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-nodeNum=30</small>
                </div>
            </div>
        </div>
        <br>
        <p>Control sample size:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <div>-t=node <b style="color: #61080f">-ss=1000</b> -sd=1</div>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//sn250.png" class="card-img-top" alt="-ss=250">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-ss=250</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//sn500.png" class="card-img-top" alt="-ss=500">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-ss=500</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//sn1000.png" class="card-img-top" alt="-ss=1000">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-ss=1000</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//sn2000.png" class="card-img-top" alt="-ss=2000">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-ss=2000</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//sn4000.png" class="card-img-top" alt="-ss=4000">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">-ss=4000</small>
                </div>
            </div>
        </div>
        <br>
        <p>Control Standard Deviation:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <div>-t=node -ss=1000 <b style="color: #61080f">-sd=0.25</b> -overlap=-2 -angle=0,0</div>
            <div>-t=node -ss=1000 <b style="color: #61080f">-sd=0.5</b> -overlap=-2 -angle=0,0 <b>-ref=0</b></div>
            <div>-t=node -ss=1000 <b style="color: #61080f">-sd=1</b> -overlap=-2 -angle=0,0 <b>-ref=1</b></div>
            <div>-t=node -ss=1000 <b style="color: #61080f">-sd=1.5</b> -overlap=-2 -angle=0,0 <b>-ref=2</b></div>
            <div>-t=node -ss=1000 <b style="color: #61080f">-sd=2</b> -overlap=-2 -angle=0,0 <b>-ref=3</b></div>
            <div>-t=node -ss=1000 <b style="color: #61080f">-sd=2.5</b> -overlap=-2 -angle=0,0 <b>-ref=4</b></div>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//sd.png" class="card-img-top" alt="-sd">
                <!--<div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">-->
                    <!--<small class="text-muted">-sd=0.25</small>-->
                <!--</div>-->
            </div>
        </div>
        <br>
        <p>Control Overlap:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <div>-t=node -ss=1000 -sd=1 <b style="color: #61080f">-overlap=-10</b> -angle=0,0</div>
            <div>-t=node -ss=1000 -sd=1 <b style="color: #61080f">-overlap=-5</b> -angle=0,0 <b>-ref=0</b></div>
            <div>-t=node -ss=1000 -sd=1 <b style="color: #61080f">-overlap=-2.5</b> -angle=0,0 <b>-ref=1</b></div>
            <div>-t=node -ss=1000 -sd=1 <b style="color: #61080f">-overlap=0</b> -angle=0,0 <b>-ref=2</b></div>
            <div>-t=node -ss=1000 -sd=1 <b style="color: #61080f">-overlap=0.5</b> -angle=0,0 <b>-ref=3</b></div>
            <div>-t=node -ss=1000 -sd=1 <b style="color: #61080f">-overlap=1</b> -angle=0,0 <b>-ref=4</b></div>
            <div>-t=node -ss=1000 -sd=1 <b style="color: #61080f">-overlap=1.5</b> -angle=0,0 <b>-ref=5</b></div>
            <div>-t=node -ss=1000 -sd=1 <b style="color: #61080f">-overlap=2</b> -angle=0,0 <b>-ref=6</b></div>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//overlap.png" class="card-img-top" alt="-overlap">
            </div>
        </div>
        <br>
        <p>Control Angle:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem; overflow-y: auto; height: 15vh">
            <div>-t=node -ss=200 -sd=1</div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,30</b> <b>-ref=0</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,45</b> <b>-ref=1</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,60</b> <b>-ref=2</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,60</b> <b>-ref=3</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,45</b> <b>-ref=4</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,30</b> <b>-ref=5</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,0</b> <b>-ref=6</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,330</b> <b>-ref=7</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,315</b> <b> -ref=8</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,300</b> <b> -ref=9</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,300</b> <b> -ref=10</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,315</b> <b> -ref=11</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,330</b> <b> -ref=12</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,0</b> <b> -ref=13</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,0</b> <b> -ref=14</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,30</b> <b> -ref=15</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,45</b> <b> -ref=16</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,60</b> <b> -ref=17</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,60</b> <b> -ref=18</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,45</b> <b> -ref=19</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,30</b> <b> -ref=20</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,0</b> <b> -ref=21</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,330</b> <b> -ref=22</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,315</b> <b> -ref=23</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,300</b> <b> -ref=24</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,300</b> <b> -ref=25</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,315</b> <b> -ref=26</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,330</b> <b> -ref=27</b></div>
            <div>-t=node -ss=200 -sd=1 -overlap=-0.5 <b style="color: #61080f">-angle=0,0</b> <b> -ref=28</b></div>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//angle.png" class="card-img-top" alt="-angle">
            </div>
        </div>
    </div>
</div>
<br>
<div class="card shadow-lg" style="box-shadow:0 1rem 3rem rgba(0,0,0,.175) !important;position:relative;display:flex;flex-direction:column;border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
    <div class="card-header" style="border-radius:calc(.25rem - 1px) calc(.25rem - 1px) 0 0;padding:.75rem 1.25rem;margin-bottom:0;background-color:rgba(0,0,0,.03);border-bottom:1px solid rgba(0,0,0,.125)">
        <b>nodeFix</b><i>: Add UCs by absolute positioning</i>
    </div>
    <div class="card-body" style="flex:1 1 auto;min-height:1px;padding:1.25rem">
        <p>All parameters:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            -t=nodeFix -ss=1000 -sd=1 -coordinate=0 -label=0
        </div>
        <p>Examples:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <div>-t=nodeFix -ss=1000 -sd=1 <b style="color: #61080f">-coordinate=0,0</b></div>
            <div>-t=nodeFix -ss=1000 -sd=1 <b style="color: #61080f">-coordinate=10,0</b></div>
            <div>-t=nodeFix -ss=1000 -sd=1 <b style="color: #61080f">-coordinate=20,2</b></div>
            <div>-t=nodeFix -ss=1000 -sd=1 <b style="color: #61080f">-coordinate=20,-2</b></div>
            <div>-t=nodeFix -ss=1000 -sd=1 <b style="color: #61080f">-coordinate=30,0</b></div>
            <div>-t=nodeFix -ss=1000 -sd=1 <b style="color: #61080f">-coordinate=40,0</b></div>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//nodeFix.png" class="card-img-top" alt="-angle">
            </div>
        </div>
    </div>
</div>
<br>
<div class="card shadow-lg" style="box-shadow:0 1rem 3rem rgba(0,0,0,.175) !important;position:relative;display:flex;flex-direction:column;border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
    <div class="card-header" style="border-radius:calc(.25rem - 1px) calc(.25rem - 1px) 0 0;padding:.75rem 1.25rem;margin-bottom:0;background-color:rgba(0,0,0,.03);border-bottom:1px solid rgba(0,0,0,.125)">
        <b>bezier</b><i>: Add Bezier curve-based clusters (BCs)</i>
    </div>
    <div class="card-body" style="flex:1 1 auto;min-height:1px;padding:1.25rem">
        <p>All parameters:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            -t=bezier -bezierNum=1 -ss=1000 -rss=1 -sd=1 -rsd=0 -control=0,0 -label=0 -offset=0
            <hr style="border-color: #c49f52;margin-top:1rem;margin-bottom:1rem;border-top:1px solid rgba(0,0,0,.1)"">
            <div style="margin-top: 10px;color: #736e63"><i>"-label" is specified only if multiple clusters need to be merged.</i></div>
            <div style="margin-top: 5px;color: #736e63"><i>"-offset" is specified only if the BC need to be translated.</i></div>
        </div>
        <p>Examples:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            -t=bezier -ss=500 -rss=0 <b style="color: #61080f">-sd=0.1</b> -rsd=0 -control=6,6,12,0,6,-6<br>
            -t=bezier -ss=500 -rss=0 <b style="color: #61080f">-sd=0.3</b> -rsd=0 -control=12,6,18,0,12,-6<br>
            -t=bezier -ss=500 -rss=0 <b style="color: #61080f">-sd=0.5</b> -rsd=0 -control=18,6,24,0,18,-6<br>
            -t=bezier -ss=500 <b style="color: #61080f">-rss=-50</b> -sd=0.1 <b style="color: #61080f">-rsd=5</b> -control=24,6,30,0,24,-6<br>
            -t=bezier -ss=500 <b style="color: #61080f">-rss=50</b> -sd=0.1 <b style="color: #61080f">-rsd=5</b> -control=30,6,36,0,30,-6<br>
            -t=bezier -ss=500 <b style="color: #61080f">-rss=-50</b> -sd=0.5 <b style="color: #61080f">-rsd=-5</b> -control=36,6,42,0,36,-6<br>
            -t=bezier -ss=500 <b style="color: #61080f">-rss=50</b> -sd=0.5 <b style="color: #61080f">-rsd=-5</b> -control=42,6,48,0,42,-6<br>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//bezier.png" class="card-img-top" alt="bezier">
            </div>
        </div>
    </div>
</div>
<br>
<div class="card shadow-lg" style="box-shadow:0 1rem 3rem rgba(0,0,0,.175) !important;position:relative;display:flex;flex-direction:column;border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
    <div class="card-header" style="border-radius:calc(.25rem - 1px) calc(.25rem - 1px) 0 0;padding:.75rem 1.25rem;margin-bottom:0;background-color:rgba(0,0,0,.03);border-bottom:1px solid rgba(0,0,0,.125)">
        Splice clusters with <i>-label</i>
    </div>
    <div class="card-body" style="flex:1 1 auto;min-height:1px;padding:1.25rem">
        <p>Examples:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            -t=node -ss=1000 -sd=1 -overlap=0 -angle=0,0<br>
            -t=node -ss=1000 -sd=1 -overlap=0 -ref=0 -angle=0,0<br>
            -t=node -ss=1000 -sd=1 -overlap=-2 -ref=1 -angle=0,0<br>
            -t=node -ss=1000 -sd=1 -overlap=0 -ref=2 -angle=0,0 <b style="color: #61080f">-label=2</b><br>
            -t=bezier -ss=1000 -rss=-10 -sd=0.5 -rsd=-3 -control=40,5,32,5,32,-5,40,-5<br>
            -t=bezier -ss=1000 -rss=-10 -sd=0.5 -rsd=-3 -control=40,5,48,5,48,-5,40,-5<br>
            -t=bezier -ss=1000 -rss=-10 -sd=0.5 -rsd=-3 -control=60,5,52,5,52,-5,60,-5<br>
            -t=bezier -ss=1000 -rss=-10 -sd=0.5 -rsd=-3 -control=60,5,68,5,68,-5,60,-5 <b style="color: #61080f">-label=6</b><br>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//label.png" class="card-img-top" alt="-label">
            </div>
        </div>
    </div>
</div>
<br>
<div class="card shadow-lg" style="box-shadow:0 1rem 3rem rgba(0,0,0,.175) !important;position:relative;display:flex;flex-direction:column;border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
    <div class="card-header" style="border-radius:calc(.25rem - 1px) calc(.25rem - 1px) 0 0;padding:.75rem 1.25rem;margin-bottom:0;background-color:rgba(0,0,0,.03);border-bottom:1px solid rgba(0,0,0,.125)">
        Add UC at the specified location of BC by <i>-ref</i>
    </div>
    <div class="card-body" style="flex:1 1 auto;min-height:1px;padding:1.25rem">
        <p>Examples:</p>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <b style="color: #6c757d">reference: </b><br>
            -t=node -ss=1000 -sd=1<br>
            -t=node -ss=1000 -sd=1 -overlap=0 <b style="color: #61080f">-ref=0</b> -angle=0,0<br>
            -t=bezier -ss=1000 -rss=0 -sd=0.3 -rsd=0 -control=10,0,16,5,22,0<br>
            -t=node -ss=1000 -sd=1 -overlap=0 <b style="color: #61080f">-ref=202</b> -angle=0,0<br>
            -t=node -ss=1000 -sd=1 -overlap=0 <b style="color: #61080f">-ref=203</b> -angle=0,0<br>
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//bcref1.png" class="card-img-top" alt="reference">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">reference</small>
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//bcref2.png" class="card-img-top" alt="-ss=500">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">In the 20% position</small>
                    <!--<small class="text-muted">-t=node -ss=500 -sd=0.3 -overlap=-10 -ref=21 -angle=0,270 -cross=1</small>-->
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//bcref3.png" class="card-img-top" alt="-ss=1000">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">In the 50% position</small>
                    <!--<small class="text-muted">-t=node -ss=500 -sd=0.3 -overlap=-10 -ref=101 -angle=0,270 -cross=1</small>-->
                </div>
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//bcref4.png" class="card-img-top" alt="-ss=1000">
                <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
                    <small class="text-muted">In the 80% position</small>
                    <!--<small class="text-muted">-t=node -ss=500 -sd=0.3 -overlap=-10 -ref=161 -angle=0,270 -cross=1</small>-->
                </div>
            </div>
        </div>
        <div class="card-footer" style="padding:.75rem 1.25rem;background-color:rgba(0,0,0,.03);border-top:1px solid rgba(0,0,0,.125)">
            <small class="text-muted"><b>20%: </b><t style="color: #856404;">-t=node -ss=500 -sd=0.3 -overlap=-10 <b style="color: #61080f">-ref=41</b> -angle=0,270 -cross=1</t></small>
            <i style="color: #6c757d;font-size: 14px"> (41=200*20%+1)</i><br>
            <small class="text-muted"><b>50%: </b><t style="color: #856404;">-t=node -ss=500 -sd=0.3 -overlap=-10 <b style="color: #61080f">-ref=101</b> -angle=0,270 -cross=1</t></small>
            <i style="color: #6c757d;font-size: 14px"> (101=200*50%+1)</i><br>
            <small class="text-muted"><b>80%: </b><t style="color: #856404;">-t=node -ss=500 -sd=0.3 -overlap=-10 <b style="color: #61080f">-ref=161</b> -angle=0,270 -cross=1</t></small>
            <i style="color: #6c757d;font-size: 14px"> (161=200*80%+1)</i><br>
        </div>
    </div>
</div>
<br>
<div class="card shadow-lg" style="box-shadow:0 1rem 3rem rgba(0,0,0,.175) !important;position:relative;display:flex;flex-direction:column;border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
    <div class="card-header" style="border-radius:calc(.25rem - 1px) calc(.25rem - 1px) 0 0;padding:.75rem 1.25rem;margin-bottom:0;background-color:rgba(0,0,0,.03);border-bottom:1px solid rgba(0,0,0,.125)">
        Randomization
    </div>
    <div class="card-body" style="flex:1 1 auto;min-height:1px;padding:1.25rem">
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <b style="color: #6c757d">Random UCs: </b><br>
            -t=node -nodeNum=5
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomUC1.png" class="card-img-top" alt="randomUC">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomUC2.png" class="card-img-top" alt="randomUC">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomUC3.png" class="card-img-top" alt="randomUC">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomUC4.png" class="card-img-top" alt="randomUC">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomUC5.png" class="card-img-top" alt="randomUC">
            </div>
        </div>
        <br>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <b style="color: #6c757d">Random BCs: </b><br>
            -t=bezier -bezierNum=5
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomBC1.png" class="card-img-top" alt="randomBC">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomBC2.png" class="card-img-top" alt="randomBC">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomBC3.png" class="card-img-top" alt="randomBC">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomBC4.png" class="card-img-top" alt="randomBC">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//randomBC5.png" class="card-img-top" alt="randomBC">
            </div>
        </div>
        <br>
        <div class="alert alert-warning" role="alert" style="font-size: 14px;color:#856404;background-color:#fff3cd;padding:.75rem 1.25rem">
            <b style="color: #6c757d">Random 5BCs + 5UCs: </b><br>
            -t=bezier -bezierNum=5<br>
            -t=node -nodeNum=5
        </div>
        <div class="card-group" style="display:flex">
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//random1.png" class="card-img-top" alt="random">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//random2.png" class="card-img-top" alt="random">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//random3.png" class="card-img-top" alt="random">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//random4.png" class="card-img-top" alt="random">
            </div>
            <div class="card" style="border:1px solid rgba(0,0,0,.125);border-radius:.25rem">
                <img src="pic//random5.png" class="card-img-top" alt="random">
            </div>
        </div>
        <br>
    </div>
</div>
<br>
<h4 id="Arguments" style="padding: 20px 0">Arguments</h4>
<table class="table table-sm table-easy table-hover table-striped align-middle">
    <thead><tr>
        <th>Parameters</th><th>Description</th><th>Value</th><th>Example</th><th>Default</th>
    </tr>
    </thead>
    <tbody style="font-size: 14px; line-height: 15px">
    <tr><td colspan="5"><b style="color: #8b3403;">Public parameters</b></td></tr>
    <tr><td>-d</td><td>Dimension</td><td>Positive integer</td><td>-d=2</td><td>2</td></tr>
    <tr><td>-o</td><td>Output</td><td>FilePath</td><td>-o=/home/work/<hr style="margin-top: 5px;margin-bottom: 5px">-o=E:/work/<hr style="margin-top: 5px;margin-bottom: 5px">-o=/home/coordiante.txt</td><td>Current directory</td></tr>
    <tr><td>-rg</td><td>
        <p>Regenerate according to the original parameters in a parameter configure file</p>
    </td><td>FilePath</td><td>-rg=/home/myConfig.txt</td><td><i>None</i></td></tr>
    <tr><td>-rp</td><td>
        <p>Reproduce according to a parameter configure file</p>
    </td><td>FilePath</td><td>-rp=/home/myConfig.txt</td><td><i>None</i></td></tr>
    <tr><td colspan="5"><b style="color: #8b3403;">Private parameters</b></td></tr>
    <tr><td>-t</td><td>
            <p>Three generation models:</p>
            <p style="margin-bottom: 5px;padding-left: 10px">node = add UCs using relative positioning</p>
            <p style="margin-bottom: 5px;padding-left: 10px">nodeFix = add UCs using absolute positioning</p>
            <p style="margin-bottom: 5px;padding-left: 10px">bezier = add BCs</p>
        </td><td>node/nodeFix/bezier</td><td>-t=node</td><td>Required if "-rg/rp" is not specified</td></tr>
    <tr><td colspan="5" style="color: #8b3403;">Parameters for <i>-t=node</i></td></tr>
    <tr><td>-nodeNum</td><td>The number of UCs to add to the current layer</td><td>Positive integer</td><td>-nodeNum=5</td><td>1</td></tr>
    <tr><td>-ss</td><td>The sample size for each cluster to add to the current layer</td><td>Positive integer</td><td>-ss=500</td><td>300��(1+SD��random(0,1))</td></tr>
    <tr><td>-sd</td><td>The standard deviation of normal distribution</td><td>Positive number</td><td>-sd=1</td><td>1+10��random(0,1)</td></tr>
    <tr><td>-ref</td><td>The reference UC for the new cluster. All UCs are numbered sequentially from 0-n. Each BC consists of multiple (default 200) UCs.</td><td>A positive integer less than the number of added UCs.</td><td>-ref=1</td><td>round(random(0,1)��UC_Num)</td></tr>
    <tr><td>-overlap</td><td>The overlap between the new cluster and the reference cluster. It is also the largest overlap between the new cluster and other clusters.</td><td>Number</td><td>-overlap=0</td><td>[0.7��random(0,1), -1��random(0,1)]</td></tr>
    <tr><td>-angle</td><td>The vector of the new cluster��s counterclockwise rotation angle, relative to the reference UC in each dimension</td><td>Number vector. The first dimension does not need to be rotated and is denoted as 0</td><td>-angle=0,30</td><td>360��random(0,1)</td></tr>
    <tr><td>-label</td><td>Specify a label for the new cluster</td><td>Integer</td><td>-label=1</td><td>Increment</td></tr>
    <tr><td>-cross</td><td>Whether clusters with conflicting parameters are shown (default is not displayed)</td><td>0/1</td><td>-cross=0</td><td>0</td></tr>
    <tr><td colspan="5" style="color: #8b3403;">Parameters for <i>-t=nodeFix</i></td></tr>
    <tr><td>-ss</td><td colspan="4">Same as <i>-ss</i> in <i>-t=node</i></td></tr>
    <tr><td>-sd</td><td colspan="4">Same as <i>-sd</i> in <i>-t=node</i></td></tr>
    <tr><td>-label</td><td colspan="4">Same as <i>-label</i> in <i>-t=node</i></td></tr>
    <tr><td>-coordinate</td><td>The centre coordinates of the new cluster</td><td>Number vector</td><td>-coordinate=2,3</td><td>Required</td></tr>
    <tr><td colspan="5" style="color: #8b3403;">Parameters for <i>-t=bezier</i></td></tr>
    <tr><td>-bezierNum</td><td>The number of BCs to add to the current layer</td><td>Positive integer</td><td>-nodeNum=5</td><td>1</td></tr>
    <tr><td>-ss</td><td colspan="3">Same as <i>-ss</i> in <i>-t=node</i></td><td>300��(1+SD��random(0,1))</td></tr>
    <tr><td>-rss</td><td>The ratio of the ending sample size to the starting sample size</td><td>Positive numbers represent increases and negative numbers represent decreases. See publication for details.</td><td>-rss=10</td><td>[10��random(0,1), -10��random(0,1)]</td></tr>
    <tr><td>-sd</td><td colspan="3">Same as <i>-sd</i> in <i>-t=node</i></td><td>2.2-2.1��random(0,1)</td></tr>
    <tr><td>-rsd</td><td>The ratio of the ending SD to the starting SD</td><td>Positive numbers represent increases and negative numbers represent decreases. See publication for details.</td><td>-rsd=-2</td><td>[5��random(0,1), -5��random(0,1)]</td></tr>
    <tr><td>-control</td><td>The control point of the Bezier curve</td><td>Number vector. The coordinate values of each control point are separated by commas</td><td>-control=2,3,12,13,16,-3</td><td>30��max(1,SD)��random(0,1)</td></tr>
    <tr><td>-label</td><td colspan="4">Same as <i>-label</i> in <i>-t=node</i></td></tr>
    <tr><td>-offset</td><td>The translation of the new BC in each dimension</td><td>Number vector</td><td>-offset=2,3</td><td><i>None</i></td></tr>
    </tbody>
</table>
