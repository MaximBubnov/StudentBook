<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
</br>
    <#if isAdmin>
<h1 class="text-center">Subjects</h1>
</br>
</br>
<h3 class="text-center">Add Subject</h3>
</br>
<div class="container mt-5">
    <form action="/study/subjects" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Subject Name : </label>
            <div class="col-sm-6">
            <input type="text" name="name" class="form-control ${(subjectError??)?string('is-invalid', '')}"
                    placeholder="Subject Name">
                <#if subjectError??>
                    <div class="invalid-feedback">
                        ${subjectError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Code Group:  </label>
            <#list groups as group>
                <div class="form-group form-check ml-3">
                    <label><input type="checkbox" class="form-check-input" name="${group}" >${group}</label>
                </div>
            </#list>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Add</button>
    </form>
</div>

</br>
</br>
</#if>
<h3 class="text-center">Subjects List</h3>
<div class="container mt-5">
    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Group Name</th>
            <th scope="col">Name</th>
            <#if isAdmin>
            <th scope="col">#</th>
            <th scope="col">##</th>
            </#if>
        </tr>
        </thead>
        <tbody>
    <#list subjects as subject>
    <tr>
        <td>${subject.id}</td>
        <td><#list subject.groupNames as group>${group}<#sep>, </#list> </td>
        <td>${subject.name}</td>
        <#if isAdmin>
        <td><a href="/study/subject/${subject.id}">Edit</a></td>
        <td>
            <form action="/study/delete/${subject.id}" method="post">
                <input type="hidden" value="${subject.id}" name="subjectId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit" class="btn btn-primary">Delete</button>
            </form></td>
        </#if>
    </tr>
    </#list>
        </tbody>

    </table>
</div>
</@c.page>