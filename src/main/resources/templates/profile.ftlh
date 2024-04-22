<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Personal Page</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Email: <b>${user.email}</b>
        <br>
        Telephone number: <b>${user.phoneNumber}</b>
        <br>

        <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
           data-toggle="collapse" href="#profileEdit" role="button"
           aria-expanded="false" aria-controls="profileEdit">
            Edit profile
        </a>

        <div class="collapse mb-3" id="profileEdit" style="">
            <form action="/profile/edit" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">Username</label>
                    <input type="text" required class="form-control"
                           id="name" name="name" value="${user.name}">
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number</label>
                    <input type="tel" required class="form-control"
                           id="phoneNumber" name="phoneNumber" value="${user.phoneNumber}">
                </div>

                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" style="width: 100%" class="btn btn-dark">Change profile</button>
            </form>
        </div>

        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Admin panel
            </a><br>
        </#if>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/my/products">
            My products
        </a>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Log out
            </button>
        </form>
    </div>
</@t.template>