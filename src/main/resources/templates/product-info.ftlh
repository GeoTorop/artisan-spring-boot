<!DOCTYPE html>
<html>
<head>
    <title>ARTISAN</title>
</head>
<body>
<h1>ARTISAN</h1>
<hr>
<h4>Detailed info about your piece of art:</h4>
<#list images as img>
    <img src="/images/${img.id}" height="60px"/><br><br>
</#list>
<b>Name of the product: </b>${product.title}<br>
<b>Description: </b>${product.description}<br>
<b>Price: </b>${product.price}<br>
<b>City: </b>${product.city}<br>
<b>Author: </b><a href="/user/${product.user.id}">${product.user.name}</a><br>
</body>
</html>