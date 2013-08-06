<%--
  Created by IntelliJ IDEA.
  User: hch
  Date: 13-2-24
  Time: 上午9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>tree</title>

    <style type="text/css">
        ul {
            padding: 0;
            margin: 0;
        }

        li {
            list-style: none;
            padding: 0;
            margin: 0;
            height: 18px;
            line-height: 18px;
        }

        ul li a {
            padding: 0;
            margin: 0;
            vertical-align: top;
            float: left;
            font-size: 12px;
        }

        .icon {
            width: 18px;
            height: 18px;
            background: url(../image/tree-icons.png);
            background-repeat: no-repeat;

            display: inline-block;
        }

        .plus011 {
            background-position: -20px 0px;
        }

        .plus111 {
            background-position: -20px -20px;
        }

        .plus110 {
            background-position: -20px -40px;
        }

        .plus010 {
            background-position: -20px -60px;
        }

        .minus011 {
            background-position: -40px 0px;
        }

        .minus111 {
            background-position: -40px -20px;
        }

        .minus110 {
            background-position: -40px -40px;
        }

        .minus010 {
            background-position: -40px -60px;
        }

        .line011 {
            background-position: 0 0;
        }

        .line111 {
            background-position: 0 -20px;
        }

        .line110 {
            background-position: 0 -40px;
        }
    </style>

    <script type="text/javascript" src="../script/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        var Tree = function() {
            var root = null;

        }
    </script>
</head>
<body style="padding: 100px">

<ul>
    <li>
        <a class="icon plus011"></a>
        aaa
    </li>
    <li>
        <a class="icon plus111"></a>
        <a>aaa</a>
    </li>
    <li>
        <a class="icon plus110"></a>
    </li>
    <li>
        <a class="icon plus010"></a>
    </li>
    <li>
        <a class="icon minus011"></a>
    </li>
    <li>
        <a class="icon minus111"></a>
    </li>
    <li>
        <a class="icon minus110"></a>
    </li>
    <li>
        <a class="icon minus010"></a>
    </li>
    <li>
        <a class="icon line011"></a>
    </li>
    <li>
        <a class="icon line111"></a>
    </li>
    <li>
        <a class="icon line110"></a>
    </li>
</ul>


</body>
</html>