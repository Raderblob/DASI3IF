
var client;
var selectedMedium;

function initDonneesProfileClient(){       
    // Extraire le numero de l'étudiant de l'URL: URL.html?numeroEtudiant=<numero>
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'getClient'},
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
    parseConsultations();
}
function parseConsultations(){       
    // Extraire le numero de l'étudiant de l'URL: URL.html?numeroEtudiant=<numero>
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'getClientConsultations',
            sortType:'DATE'},
            dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        // Récupération des données                     
        consultations = response.consultations;
        var htmlIn = "";
        consultations.forEach (function(consult){
            var txt = '"'+consult.id+'"';
            var b = "<button id='pendingConsult'>medium=" + consult.nom+"</button>";
            htmlIn = htmlIn + "<br>" + b;
        });
       $('#right').html(htmlIn);
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function goToRequestPage(){
    window.location='./requestPage.html';
}
function goToClientHome(){
    window.location='./clientHomePage.html';
}

function applyFilters(){
    var type;
    const radioSpirite = document.querySelectorAll('#Spirit');
    const radioAstrologue = document.querySelectorAll('#Astrologue');
    const radioCartomancien = document.querySelectorAll('#Cartomancien');
    if(radioSpirite[0].checked){
        type = "Spirite";
    }else if(radioAstrologue[0].checked){
        type = "Astrologue";
    }else if(radioCartomancien[0].checked){
        type = "Cartomancien";
    }
    $.ajax({// Requête AJAX43                     
        url:'./Controleur',// URL
        method:'POST',// Méthode
        data:{// Paramètres
        todo:"getFilteredMediums",
        type:type},
        dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        var inputTxt = $('#mediumNameInput').val();
        var title;
        var htmlIn = "";
        

        title = "<h2 id='listBoxDivTitle'>Mediums</h2>";
        var mediums=response.mediums;
        mediums.forEach (function(med){
            if(inputTxt == "" | med.nom.includes(inputTxt)){
                var b = "<button id='"+ med.nom +"Button' onClick='showMediumDetails("+ '"' +med.nom +'"' +")'>" + med.nom + "</button>";
                htmlIn = htmlIn + "<br>" + b;
            }
        });


        
        $('#clickableMediums').html(title + htmlIn);

    })
    .fail(function(error){// Appel KO => erreur technique à gérer
        console.log('Erreur:',error);// LOG sur la Console Javascript
        alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function showMediumDetails(mediumName){
    $.ajax({// Requête AJAX43                     
        url:'./Controleur',// URL
        method:'POST',// Méthode
        data:{// Paramètres
        todo:"getMedium",
        name:mediumName},
        dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        var inputTxt = $('#mediumNameInput').val();
        var title;
        var htmlIn = "";
        

        title = "<h2>Medium Details</h2>";
        var medium=response.medium;
        selectedMedium = medium;
        htmlIn = "<br>" + medium.nom + "<br>" + medium.presentation + "<br>" + medium.formation + "<br>" + medium.promotion + "<br>" + medium.support;


        
        $('#MediumDetailDiv').html(title + htmlIn);

    })
    .fail(function(error){// Appel KO => erreur technique à gérer
        console.log('Erreur:',error);// LOG sur la Console Javascript
        alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function sendConsultationsRequest(){
    
}