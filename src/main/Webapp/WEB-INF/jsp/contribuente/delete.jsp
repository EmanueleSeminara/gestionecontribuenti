<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Elimina elemento</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Elimina elemento
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${delete_contribuente_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Nome</dt>
					  <dd class="col-sm-9">${delete_contribuente_attr.nome}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Cognome:</dt>
					  <dd class="col-sm-9">${delete_contribuente_attr.cognome}</dd>
			    	</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
					  <dd class="col-sm-9">${delete_contribuente_attr.codiceFiscale}</dd>
					</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data di nascita:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_contribuente_attr.dataDiNascita}" /></dd>
			    	</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Indirizzo:</dt>
					  <dd class="col-sm-9">${delete_contribuente_attr.indirizzo}</dd>
					</dl>
			    	
					</div>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        
			        <form method="post" action="${pageContext.request.contextPath}/contribuente/remove">
						<input type="text" hidden="" name="idDaRimuovere" value="${delete_contribuente_attr.id}">
						<div class="col-12">
							<a href="${pageContext.request.contextPath }/contribuente/" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i> Back
					        </a>
							<button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-danger">Confirm</button>
						</div>
					</form>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>