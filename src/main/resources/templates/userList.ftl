<#import "parts/common.ftl" as c>

<@c.page>
</br>
    <h1 class="text-center">List of Users</h1>
<div class="container mt-5">
<table class="table table-striped">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Name</th>
        <th scope="col">Password</th>
        <th scope="col">Role</th>
        <th scope="col">#</th>
        <th scope="col">##</th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id}">Edit</a></td>
            <td>
                <form action="/user/delete/${user.id}" method="post">
                    <input type="hidden" value="${user.id}" name="userId">
                    <input type="hidden" value="${_csrf.token}" name="_csrf">
                    <button type="submit" class="btn btn-primary">Delete</button>
                </form></td>
        </tr>
    </#list>
    </tbody>
</table>
</div>
</@c.page>