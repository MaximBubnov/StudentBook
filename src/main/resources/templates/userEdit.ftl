<#import "parts/common.ftl" as c>

<@c.page>

<h1 class="text-center">User Editor</h1>
<div class="container mt-5">
<form action="/user" method="post">
    <div class="form-group col-sm-3 ">
    <input type="text" class="form-control" name="username" value="${user.username}">
    </div>
    <#list roles as role>
        <div class="form-group form-check ml-3">
            <label><input type="checkbox" class="form-check-input" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary ml-3">Save</button>
</form>
</div>
</@c.page>