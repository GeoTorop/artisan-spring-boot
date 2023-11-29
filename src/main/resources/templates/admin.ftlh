<!DOCTYPE html>
<html>
<head>
    <title>ARTISAN</title>
</head>
<body>
<h1>ARTISAN</h1>
<hr>
<h4>Admin panel</h4>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Telephone number</th>
        <th>Activity</th>
        <th>Roles</th>
        <th>Ban user</th>
        <th>Detailed info</th>
        <th>Editing</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email}</th>
            <th>${user.phoneNumber}</th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}</#list></th>
            <th>
                <form action="/admin/user/ban/${user.id}" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="Ban user">
                </form>
            </th>
            <th><a href="/admin/user/edit/${user.id}">Editing</a></th>
            <th><a href="/user/${user.id}">Detailed info</a></th>
        </tr>
    <#else>
        <h3>No users found</h3>
    </#list>
</table>
</body>
</html>