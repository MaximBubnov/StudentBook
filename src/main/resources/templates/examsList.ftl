<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
</br>
    <h1 class="text-center">My Exams</h1>
</br>
<h3 class="text-center">Add Exams</h3>
</br>
<div class="container mt-5">
    <form action="/exams/examAdd" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name : </label>
            <input  disabled type="text" name="user" value="${username}">
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Subject Name : </label>
            <div class="col-sm-3">
                <select name="subject">
                    <#list subjects as subject>
                        <option value="${subject.id}">${subject.name}</option>
                    </#list>
                </select>

                <#if subjectError??>
                    <div class="invalid-feedback">
                        ${subjectError}
                    </div>
                </#if>
            </div>
        </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> Date </label>
                <div class="col-sm-3">
                    <input type="date" name="date">
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary">Add Exam</button>
    </form>
</div>
<h3 class="text-center">Exams List</h3>
<div class="container mt-5">
    <table class="table table-striped">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">User Name</th>
            <th scope="col">Name Subject</th>
            <th scope="col">Date</th>
            <th scope="col">#</th>
            <th scope="col">##</th>
        </tr>
        </thead>
        <tbody>
    <#list examsList as exam>
    <tr>
        <td>${exam.id}</td>
        <td>${exam.user.username}</td>
        <td>${exam.subject.name}</td>
        <td><#if exam.date??>${exam.date}</#if></td>
        <td><a href="/exams/edit/${exam.id}">Edit</a></td>
        <td>
            <form action="/exams/delete/${exam.id}" method="post">
                <input type="hidden" value="${exam.id}" name="examId">
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <button type="submit" class="btn btn-primary">Delete</button>
            </form></td>

    </tr>
    </#list>
        </tbody>

    </table>
</@c.page>