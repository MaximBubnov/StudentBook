<#import "parts/common.ftl" as c>

<@c.page>
    <h1 class="text-center">Subject Editor</h1>
</br>
<div class="container mt-5">
<form action="/study" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Subject Name : </label>
        <div class="col-sm-6">
        <input type="text" class="form-control" name="name" value="${subject.name}">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Code Group:  </label>
    <#list groups as group>
         <div class="form-group form-check ml-3">
             <label><input type="checkbox" class="form-check-input" name="${group}" ${subject.groupNames?seq_contains(group)?string("checked", "")}>${group}</label>
         </div>
    </#list>
    </div>
    <input type="hidden" value="${subject.id}" name="subjectId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit" class="btn btn-primary">Save</button>
</form>
</div>
</@c.page>