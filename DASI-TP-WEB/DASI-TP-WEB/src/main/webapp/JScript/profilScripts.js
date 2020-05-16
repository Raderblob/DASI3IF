/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var client;
var returnId;
function initDonneesProfileClient(){
    console.log('hello');         
    // Extraire le numero de l'étudiant de l'URL: URL.html?numeroEtudiant=<numero>
    var queryString = decodeURIComponent(window.location.search);
    var login=queryString.split("?")[1];
    returnId = queryString.split("?")[2]
    console.log(login);
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'getClient',
            login:login},
            dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        // Récupération des données                     
        client = response.personne;
        $('#astralProfil').html("Name: "+ client.prenom + " " + client.nom + "<br>Date of Birth: " + client.BirthDate + "<br>Profil Astral:<br>"  + "-" + client.AnimalTotem + "<br>-" + client.CouleurPorteBonheur + "<br>-" + client.SigneAstroChinois + "<br>-" + client.SigneZodiac);
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function getConsultByDate(){
    parseConsultations("DATE");
}
function getConsultByMedium(){
    parseConsultations("MEDIUM");
}
function getConsultByEmployee(){
    parseConsultations("EMPLOYEE");
}
function parseConsultations(sortType){       
    // Extraire le numero de l'étudiant de l'URL: URL.html?numeroEtudiant=<numero>
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'getClientConsultations',
            clientEmail:client.mail,
            sortType:sortType},
            dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        // Récupération des données                     
        consultations = response.consultations;
        var htmlIn = "";
        consultations.forEach (function(consult){
            var txt = '"'+consult.id+'"';
            var b = "<button id='pendingConsult" + consult.id + "' onClick='getConsultDetails("+ txt +")' cId='" + consult.id +"'>medium=" + consult.nom+"</button>";
            htmlIn = htmlIn + "<br>" + b;
        });
       $('#HistConsultations').html(htmlIn);
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function getConsultDetails(consultId){
// Extraire le numero de l'étudiant de l'URL: URL.html?numeroEtudiant=<numero>
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'getConsultation',
            consultationId:consultId},
            dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
    // Récupération des données                     
    consultation = response.consultations[0];
    var htmlIn="";
    htmlIn = htmlIn + "Medium: " + consultation.nom + "<br>";
    htmlIn = htmlIn + "state: " + consultation.state + "<br>";
    htmlIn = htmlIn + "employee: " + consultation.acceptor + "<br>";
    htmlIn = htmlIn + "date: " + consultation.date + "<br>";
    htmlIn = htmlIn + "startHour: " + consultation.startHour + "<br>";
    htmlIn = htmlIn + "endHour: " + consultation.endHour + "<br>";
    htmlIn = htmlIn + "comment: " + consultation.comment + "<br>";
    htmlIn = htmlIn + "length: " + consultation.length + "<br>";
    
    $('#consultDetails').html(htmlIn);
    
    
    
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function returnToEmployee(){
    window.location='./consultationDetails.html?'+returnId;
}