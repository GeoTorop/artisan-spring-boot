<!DOCTYPE html>
<html>
<head>
    <title>ARTISAN</title>
</head>
<body>
<h1>ARTISAN</h1>
<hr>
<h3>Detailed information about ${user.name}</h3>
<b>Telephone number: </b> ${user.phoneNumber}<br>
<b>Email: </b> ${user.email}<br>
<h4>Merchandise of the ${user.name}: </h4>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} € | <a href="/product/${product.id}">See more...</a></p>
    </div>
    <#else>
    <h3>Sorry, but there is no merchandise available :(</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage}</h2>
</#if>
</body>
</html>