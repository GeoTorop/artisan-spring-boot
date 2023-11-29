<!DOCTYPE html>
<html>
<head>
    <title>ARTISAN</title>
</head>
<body>
<h1>ARTISAN</h1>
<hr>
<#if user.email??>
    <h3>Username: <i>${user.name}</i></h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Logout"/>
    </form>
    <#if user.isAdmin()>
        <a href="/admin">Admin panel</a></h1>
    </#if>
<#else>
    <a href="/login">Sign in</a></h1>
</#if>
<hr>
<h4>Products from craftsman's from around the globe!</h4>
<form action="/" method="get">
    Search by name of an ad: <input type="text" name="title"><br>
    <input type="submit" value="Search"/>
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} € | <a href="/product/${product.id}">See more...</a></p>
    </div>
<#else>
    <h3>No products :^(</h3>
</#list>
<#if user.email??>
    <hr>
    <h3>Create new product</h3>
    <form action="/product/create" method="post" enctype="multipart/form-data">
        Name of an ad: <input type="text" name="title"/><br><br>
        Description: <input type="text" name="description"/><br><br>
        Price: <input type="number" name="price"/><br><br>
        City: <input type="text" name="city"/><br><br>
        First image: <input type="file" name="file1"/><br><br>
        Second image: <input type="file" name="file2"/><br><br>
        Third image: <input type="file" name="file3"/><br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Add product"/>
    </form>
</#if>
</body>
</html>