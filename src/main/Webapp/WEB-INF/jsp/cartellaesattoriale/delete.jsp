<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
</head>
<body class="d-flex flex-column h-100">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header'>
			        Visualizza dettaglio
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Id:</dt>
					  <dd class="col-sm-9">${delete_cartellaEsattoriale_attr.id}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Descrizione:</dt>
					  <dd class="col-sm-9">${delete_cartellaEsattoriale_attr.descrizione}</dd>
			    	</dl>
			    	
					  <dl class="row">
					  <dt class="col-sm-3 text-right">Stato:</dt>
					  <dd class="col-sm-9">${delete_cartellaEsattoriale_attr.stato}</dd>
					</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Importo:</dt>
					  <dd class="col-sm-9">${delete_cartellaEsattoriale_attr.importo}</dd>
			    	</dl>
			    	
			    	<!-- info Contribuente -->
			    	<p>
					  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
					    Info Contribuente
					  </a>
					</p>
					<div class="collapse" id="collapseExample">
					  <div class="card card-body">
					  	<dl class="row">
					  <dt class="col-sm-3 text-right">Nome</dt>
					  <dd class="col-sm-9">${delete_cartellaEsattoriale_attr.contribuente.nome}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Cognome:</dt>
					  <dd class="col-sm-9">${delete_cartellaEsattoriale_attr.contribuente.cognome}</dd>
			    	</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
					  <dd class="col-sm-9">${delete_cartellaEsattoriale_attr.contribuente.codiceFiscale}</dd>
					</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Data di nascita:</dt>
					  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_cartellaEsattoriale_attr.contribuente.dataDiNascita}" /></dd>
			    	</dl>
			    	<dl class="row">
					  <dt class="col-sm-3 text-right">Indirizzo:</dt>
					  <dd class="col-sm-9">${delete_cartellaEsattoriale_attr.contribuente.indirizzo}</dd>
					</dl>
					    
					  </div>
					<!-- end info Contribuente -->
					</div>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <form method="post" action="${pageContext.request.contextPath}/cartellaesattoriale/remove">
						<input type="text" hidden="" name="idDaRimuovere" value="${delete_cartellaEsattoriale_attr.id}">
						<div class="col-12">
							<a href="${pageContext.request.contextPath }/cartellaesattoriale/" class='btn btn-outline-secondary' style='width:80px'>
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