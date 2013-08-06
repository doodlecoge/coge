<%--
  Created by IntelliJ IDEA.
  User: hch
  Date: 13-2-23
  Time: 下午1:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<!--[if IE 7 ]>		 <html class="no-js ie ie7 lte7 lte8 lte9" lang="en-US"> <![endif]-->
<!--[if IE 8 ]>		 <html class="no-js ie ie8 lte8 lte9" lang="en-US"> <![endif]-->
<!--[if IE 9 ]>		 <html class="no-js ie ie9 lte9>" lang="en-US"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html class="no-js" lang="en-US"> <!--<![endif]-->
<head>
    <title>Modernizr Javascript</title>

    <style type="text/css">
        #rotate {
            border: 1px solid #444;
            width: 200px;
            height: 200px;
            line-height: 200px;
            text-align: center;

            /*-webkit-transform: rotateZ(5deg);*/
            -webkit-transition: -webkit-transform 50s ease-in-out;


        }

        #rotate:hover {

            -webkit-transform: rotateZ(160deg);


            transform:          skew(360deg,0deg);
            -ms-transform:      skew(360deg,0deg); /* IE 9 */
            -webkit-transform:  skew(360deg,0deg); /* Safari and Chrome */
            -o-transform:       skew(360deg,0deg); /* Opera */
            -moz-transform:     skew(360deg,0deg); /* Firefox */


        }
    </style>

    <script type="text/javascript" src="../script/modernizr-latest.js"></script>
</head>
<body>
    <div id="rotate">
        ABC
    </div>
</body>
</html>