<#import "parts/common.ftl" as c>

<@c.page>
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