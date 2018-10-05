            $(document).ready(function(){
                var line1 = [1,4,11,22,5,11,0,4,3,8,1,16,4,4,0,0,6,2,0,3,9,20,6,3,1,5,2,14,11,8,1,1,1,0,5,0,3,2,0,1,0,1,6,3,7,4,3,0,2,0,0,0,1,0,0,1,0,4,2,4,4,0,1,2,2,0,0,0,2,0,1,3,0,2,4,19,2,0,0,1,7,1,11,1,0,1,1,8,0,1,8,0,2,3,0,1,6,4,0,7,3,0,0,0,4,4,0,2,0,3,5,0,6,0,2,9,0,2,9,4,0,0,9,11,4,3,1,6,10,2,4,2,1,0,4,2,2,0,1,2,5,0,3,0,9,10,0,0,1,4,2,1,1,0,2,0,0,8,6,5,0,6,10,5,5,0,21,7,14,4,2,4,0,0,0,8,1,3,1,0,6,4,6,0,0,1,0,1,0,3,1,1,5,2,0,0,1,1,8,9,13,14,1,0,0];
                var line2 = [0,0,5,0,7,1,4,0,0,15,22,7,8,8,5,6,0,1,12,9,3,3,6,2,1,3,1,9,12,3,2,1,0,6,1,7,4,2,2,1,1,0,0,0,4,0,0,1,2,3,1,1,0,3,3,2,5,1,4,2,1,1,0,3,2,2,2,2,0,0,2,0,4,2,0,3,1,1,1,0,2,10,0,2,3,4,9,2,2,1,2,2,0,1,1,0,4,0,0,5,2,0,3,9,1,1,5,0,3,0,6,6,0,5,3,1,1,0,3,2,2,9,0,0,0,7,4,0,0,5,3,0,6,7,3,2,2,2,4,1,0,9,6,9,0,1,11,1,0,0,2,1,0,1,4,9,9,1,5,0,9,9,0,4,0,9,1,2,4,1,8,1,10,4,0,5,4,2,1,1,4,0,2,2,2,1,1,0,4,1,0,9,0,8,1,1,0,0,1,0,1,0,0,1,1];
                var line3 = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];

var ticks = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205];$.jqplot('line', [line1, line2, line3], {
        animate: true,
axesDefaults:{min:0,tickInterval: 1},        seriesDefaults: {
            rendererOptions: {
                smooth: true
            }
        },
        series: [{lineWidth: 1.5, label: 'Passed'},
            {lineWidth: 1.5, label: 'Failed'},
            {lineWidth: 1.5, label: 'Skipped'}],
        axes: {
            xaxis: {
                label: "Run Number",
                ticks: ticks,
                tickOptions: {
                    formatString: "%'d "
                },
                pad: 1.2,
                rendererOptions: {
                    tickInset: 0.3,
                    minorTicks: 1
                }
            },
            yaxis: {
                label: "TC Number"
                ,tickOptions: {
                    formatString: "%'d Tc"
                },
            }
        },
        highlighter: {
            show: true,
            sizeAdjust: 10,
            tooltipLocation: 'n',
            tooltipAxes: 'y',
            tooltipFormatString: '%d :&nbsp;<b><i><span style="color:black;">Test Cases</span></i></b>',
            useAxesFormatters: false
        },
        cursor: {
            show: true
        },
        grid: {background: '#ffffff', drawGridLines: true, gridLineColor: '#cccccc', borderColor: '#cccccc',
            borderWidth: 0.5, shadow: false},
        legend: {show: true, placement: 'outside', location: 'e'},
        seriesColors: ["#7BB661", "#E03C31", "#21ABCD"]
    });
});
