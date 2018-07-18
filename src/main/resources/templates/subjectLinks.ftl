<#import "parts/common.ftl" as c>

<@c.page>
<div class="card text-center">
    <div class="card-header">
        <ul class="nav nav-pills card-header-pills">
            <li class="nav-item">
                <a class="nav-link active" href="/study/subject/info/${subject.id}">Active</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/study/subject/info/links/${subject.id}">Links</a>
            </li>
        </ul>
    </div>
    <div class="card-body">
        <h5 class="card-title">${subject.name}</h5>
        <p class="card-text">Work plan:</p>
        <p class="card-text"><a href="/pdf/${subject.filename}">${subject.filename}</a></p>

    </div>
</div>
</@c.page>