<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Search</h1><hr>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="searchCity">City</label>
                <select id="searchCity" name="searchCity" class="form-control">
                    <option value="">Choose option</option>
                    <option value="Minsk">Minsk</option>
                    <option value="Gomel">Gomel</option>
                    <option value="Brest">Brest</option>
                    <option value="Athens">Athens</option>
                    <option value="Washington">Washington</option>
                    <option value="Moscow">Moscow</option>
                </select>
            </div>
            <div class="form-group col-md-10">
                <label for="searchWord">Search by ad</label>
                <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                       id="searchWord">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Search</button>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list products as product>
            <a href="/product/${product.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <img src="/images/${product.previewImageId}" class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${product.title}</h5>
                        <p class="card-text">${product.price} €</p>
                        <#if user.email??>
                            <form action="/favorite/add/${product.id}" method="post">
                                <button type="submit" class="btn btn-light btn-primary" name="addToFavorites">Add to favorites</button>
                            </form>
                        </#if>
                    </div>
                    <div class="card-footer">
                        ${product.city}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Ad not found, try again</h1>
        </#list>
    </div>
</@t.template>