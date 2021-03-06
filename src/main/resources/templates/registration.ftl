<#import "parts/login.ftl" as l>
<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<#if isAdmin>

<@c.page>
    ${message?ifExists}
</br>
<h1 class="text-center">Create new User</h1>
<div class="container mt-5">
<form action="/registration" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> User Name : </label>
        <div class="col-sm-6">
            <input type="text" name="username"
                   class="form-control ${(usernameError??)?string('is-invalid', '')}"
                   placeholder="User name"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:  </label>
        <div class="col-sm-6">
            <input type="password" name="password"
                   class="form-control ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Password"/>
             <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
             </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Password:  </label>
        <div class="col-sm-6">
            <input type="password" name="password2"
                   class="form-control ${(password2Error??)?string('is-invalid', '')}"
                   placeholder="Petype password"/>
             <#if password2Error??>
                <div class="invalid-feedback">
                    ${password2Error}
                </div>
             </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Email: </label>
        <div class="col-sm-6">
            <input type="email" name="email"
                   class="form-control  ${(emailError??)?string('is-invalid', '')}"
                   placeholder="some@some.com"/>
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="g-recaptcha" data-sitekey="6LdtI2AUAAAAAETY3ph5csPSda3JwxheqmLLVH7q"></div>
         <#if captchaError??>
        <div class="alert alert-danger" role="alert">
            ${captchaError}
        </div>
         </#if>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button type="submit" class="btn btn-primary">Create</button>
</form>
</div>
</@c.page>
</#if>