<#import "parts/common.ftl" as c>

<@c.page>
</br>
<h1 class="text-center">Update Profile</h1>
</br>
</br>
<div class="container mt-5">
    <h5>${username}</h5>

<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:  </label>
        <div class="col-sm-4">
            <input type="password" name="password" class="form-control" placeholder="Password"/>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email: </label>
        <div class="col-sm-4">
            <input type="email" name="email" class="form-control" placeholder="some@some.com" value="${email!''}"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">Save</button>
</form>
</div>
</@c.page>