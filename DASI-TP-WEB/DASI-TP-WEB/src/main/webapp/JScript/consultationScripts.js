/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var consultation;

function initDonneesConsultationDetails(){       
    // Extraire le numero de l'étudiant de l'URL: URL.html?numeroEtudiant=<numero>
    var queryString = decodeURIComponent(window.location.search);
    var id=queryString.split("?")[1];
    console.log(id);
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'getConsultation',
            consultationId:id},
            dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
    // Récupération des données                     
    consultation = response.consultations[0];
    $('#consultationDate').val(consultation.date);
    $('#startHour').val(consultation.startHour);
    $('#endHour').val(consultation.endHour);
    $('#TextInput').val(consultation.comment);
    
    if(consultation.state == "ASSIGNED"){
        $('#submitButton').attr("disabled","true");
        $('#acceptButton').removeAttr("disabled");
    }else if(consultation.state == "ACCEPTED"){
        $('#submitButton').removeAttr("disabled");
        $('#acceptButton').attr("disabled","true");
    }else{
        $('#submitButton').attr("disabled","true");
        $('#acceptButton').attr("disabled","true");
        $('#TextInput').attr("readonly","true");
    }
    
    $("#clientInfo").html("Client Email: " + consultation.caller  + "<br>Consultation State: " + consultation.state + "<br>Medium Required: " + consultation.nom); 
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function seeClientProfil(){
    window.location='./clientProfil.html?'+consultation.caller + "?" + consultation.id;
}
function returnToHome(){
    window.location='./employeeHomePage.html';
}

function askForHelp(){
    var amour;
    var travail;
    var sante;
    const radioAmour1 = document.querySelectorAll('#amour1');
    const radioAmour2 = document.querySelectorAll('#amour2');
    const radioAmour3 = document.querySelectorAll('#amour3');
    
    const radioTravail1 = document.querySelectorAll('#travail1');
    const radioTravail2 = document.querySelectorAll('#travail2');
    const radioTravail3 = document.querySelectorAll('#travail3');
    
    const radioSante1 = document.querySelectorAll('#sante1');
    const radioSante2 = document.querySelectorAll('#sante2');
    const radioSante3 = document.querySelectorAll('#sante3');
    
    if(radioAmour1[0].checked){
        amour = 1;
    }else if(radioAmour2[0].checked){
        amour = 2;
    }else if(radioAmour3[0].checked){
        amour =3;
    }else{
        amour = 4;
    }
    if(radioTravail1[0].checked){
        travail = 1;
    }else if(radioTravail2[0].checked){
        travail = 2;
    }else if(radioTravail3[0].checked){
        amour =3;
    }else{
        travail = 4;
    }
    if(radioSante1[0].checked){
        sante = 1;
    }else if(radioSante2[0].checked){
        sante = 2;
    }else if(radioSante3[0].checked){
        amour =3;
    }else{
        sante = 4;
    }
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'getPredictions',
            login:consultation.caller,
            travail:travail,
            sante:sante,
            amour:amour},
            dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        // Récupération des données                     
        allinfo = response.AllInfo;
        var htmlIn = "Help:"
        allinfo.forEach (function(info){
            htmlIn = htmlIn + "<br><br>" + info.info;
        });
        $("#outputDiv").html(htmlIn);
    
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function acceptConsultation(){
    
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'acceptConsultation'},
            dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        // Récupération des données                     
        var resp = response.personne;
        location.reload();
        
    
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function confirmConsultation(){
   var txt = $("#TextInput").val();
   if(txt !==""){
   console.log(txt);
        $.ajax({// Requête AJAX43                     
                url:'./Controleur',// URL
                method:'POST',// Méthode
                data:{// Paramètres
                todo:'confirmConsultation',
                review:txt},
                dataType:'json'// Type de retour attendu
        })
        .done(function(response){// Appel OK => "response" contient le résultat JSON
            location.reload();


        })
        .fail(function(error){// Appel KO => erreur technique à gérer
        console.log('Erreur:',error);// LOG sur la Console Javascript
        alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
        });
   }
}