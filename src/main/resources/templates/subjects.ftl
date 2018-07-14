<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
</br>
    <#if isAdmin>
<h1 class="text-center">Subjects</h1>
</br>
<h3 class="text-center">Add Subject</h3>
</br>
<div class="container mt-5">
    <form action="/study/subjects" method="post" enctype="multipart/form-data">
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
            <label class="col-sm-2 col-form-label "> Choose file : </label>
            <div class="custom-file col-sm-5 ml-3">
                <input type="file" name="file" id="customFile">
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" for="exampleFormControlTextarea1">Description of Subject</label>
            <textarea class="form-control mx-5" id="exampleFormControlTextarea1" rows="3" name="description"></textarea>
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
</#if>
<h3 class="text-center">Subjects List</h3>
<div class="container mt-5">
    <div class="form-group row">
        <form method="get" action="/study/subjects" class="form-inline">
            <label class="ml-3 mr-5">Filter for Subjects</label> <select name="filter">
                <option value=""></option>
                     <#list groups as group>
                         <option>${group}</option>
                     </#list>
            </select>
        <#--<input type="text" name="filter" class="form-control mr-3" value="${filter?ifExists}">-->
            <button type="submit" class="btn btn-primary ml-5">Search</button>
        </form>
    </div>
</div>
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