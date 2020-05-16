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
    
    
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function seeClientProfil(){
    window.location='./clientProfil.html?'+consultation.caller + "?" + consultation.id;
}