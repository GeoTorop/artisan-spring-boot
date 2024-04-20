<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Favorites</h1>
    <hr>
    <div class="card-columns">
        <#if favorites?has_content>
            <#list favorites as favorite>
                <a href="/product/${favorite.product.id}" class="product-link">
                    <div class="card text-white bg-dark my-3 product">
                        <img src="/images/${favorite.product.previewImageId}" class="card-img-top">
                        <div class="card-body">
                            <h5 class="card-title">${favorite.product.title}</h5>
                            <p class="card-text">${favorite.product.price} €</p>
                        </div>
                        <div class="card-footer">
                            ${favorite.product.city}
                        </div>
                    </div>
                </a>
            </#list>
        <#else>
            <h1 style="text-align: center">No favorite products found</h1>
        </#if>
    </div>
</@t.template>