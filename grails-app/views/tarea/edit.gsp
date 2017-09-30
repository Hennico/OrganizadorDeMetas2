<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tarea.label', default: 'Tarea')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-tarea" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-tarea" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.tarea}">
				<ul class="errors" role="alert">
					<g:eachError bean="${this.tarea}" var="error">
						<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
            </g:hasErrors>
            <g:form resource="${this.tarea}" method="PUT">
                <g:hiddenField name="version" value="${this.tarea?.version}" />
                <fieldset class="form">
                    <f:field bean="tarea" property="nombre"/>
                    <f:field bean="tarea" property="descripcion"/>
					<br/>
					<div style="border-style:solid;border-color:black;border-width:1px;background-color:#E0FFFF;width:350px;text-align:center">
						<h1>Agregar sub meta</h1>
						<table width="300" >
							<tr>
								<td>Nombre:</td>
								<td><g:textField name="smNombre" value="" /></td>
							</tr>
							<tr>
								<td>Descripcion:</td>
								<td><g:textField name="smDescripcion" value="" /></td>
							</tr>

							<tr>
								<td>Submeta:</td>

								<td>
									<select name="TipoSubMeta" id="TipoSubMeta">
										<option value=   "Tarea">Tarea</option>
										<option value="Objetivo">Objetivo</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>Obligacion:</td>
								<td>
                  <g:select name="TipoObligacion" from="${organizadordemetas.Obligatoriedad?.values()}"/>
								</td>
							</tr>

							<tr>
								<td> &nbsp; </td>
								<td><g:actionSubmit action="agregarSubMeta" value="Crear"/></td>
							</tr>
						</table>
					</div>



          <div style="border-style:solid;border-color:black;border-width:1px;background-color:#E0FFFF;width:350px;text-align:center">
						<h1>Cambiar de estado</h1>
						<table width="300" >

							<tr>
                <td>Obligacion:</td>
                <td>
                  <g:select name="NuevoEstado" from="${organizadordemetas.Estado?.values()}" />
                </td>
							</tr>

							<tr>
								<td> &nbsp; </td>
								<td><g:actionSubmit action="cambiarEstado" value="Cambiar"/></td>
							</tr>
						</table>
					</div>



                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
