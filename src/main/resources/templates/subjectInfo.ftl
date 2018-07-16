<#import "parts/common.ftl" as c>

<@c.page>
<div class="card text-center">
    <div class="card-header">
        <ul class="nav nav-pills card-header-pills">
            <li class="nav-item">
                <a class="nav-link active" href="#">Active</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Links</a>
            </li>
        </ul>
    </div>
    <div class="card-body">
        <h5 class="card-title">${subject.name}</h5>
        <p class="card-text"><#if subject.description??>${subject.description}<#else>No description</#if></p>
        </br>
        <h5 class="card-title">Faculties:</h5>
        <p class="card-text"><#list subject.groupNames as group>${group}<#sep>, </#list> </p>
        <p class="card-text"><a href="/pdf/${subject.filename}">${subject.filename}</a></p>
        <a href="/study/subjects" class="btn btn-primary">Go Back</a>
    </div>
</div>

</@c.page>