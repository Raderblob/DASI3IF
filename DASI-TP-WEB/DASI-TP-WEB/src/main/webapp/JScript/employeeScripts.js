/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var employee;


function initDonneesHomePage(){// Fonction pour initialiser les données de la page
           console.log('hello');         
            // Extraire le numero de l'étudiant de l'URL: URL.html?numeroEtudiant=<numero>
            var queryString = decodeURIComponent(window.location.search);
            var mail=queryString.split("?")[1];
            console.log(mail);
            $.ajax({// Requête AJAX43                     
                    url:'./Controleur',// URL
                    method:'POST',// Méthode
                    data:{// Paramètres
                    todo:'getEmployee',
                    login:mail},
                    dataType:'json'// Type de retour attendu
            })
            .done(function(response){// Appel OK => "response" contient le résultat JSON
            console.log('hello2'); 
            // Récupération des données                     
            var personne=response.personne;
            employee = personne;
            getPendingRequests();
            })
            .fail(function(error){// Appel KO => erreur technique à gérer
            console.log('Erreur:',error);// LOG sur la Console Javascript
            alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
            });
        }
function debugF(){
    console.log(employee);
            $('#nom-client').html(employee.nom);
            $('#prenom-client').html(employee.prenom);
}


function getPendingRequests(){
    console.log("test42");
    $.ajax({// Requête AJAX43                     
        url:'./Controleur',// URL
        method:'POST',// Méthode
        data:{// Paramètres
        todo:'getUnansweredRequests',
        employeeEmail:employee.mail},
        dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        var cNumber = response.ConsultationsNumber;
        var consultations=response.consultations;
        if(cNumber == 1){
            var txt = '"hello"';
            var title = "<h2 id='listBoxDivTitle'>Pending Requests</h2>"
            var b = "<button id='pendingConsult' onClick='console.log("+ txt +")'>caller=" + consultations[0].caller + " medium=" + consultations[0].nom + " state=" + consultations[0].state + "</button>";
             $('#listBoxDiv').html(title + "<br>" +b);
        }else{
            var title = "<h2 id='listBoxDivTitle'>Pending Requests</h2>"
            $('#listBoxDiv').html(title + "<br>Nothing");
        }
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
        console.log('Erreur:',error);// LOG sur la Console Javascript
        alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}

function getPastConsultations(){
    console.log("test42");
    $.ajax({// Requête AJAX43                     
        url:'./Controleur',// URL
        method:'POST',// Méthode
        data:{// Paramètres
        todo:'getEmployeeConsultations',
        employeeEmail:employee.mail},
        dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        var consultations=response.consultations;
        var title = "<h2 id='listBoxDivTitle'>Pending Requests</h2>"
        var txt = '"hello"';
        var htmlIn = "";
        consultations.forEach (function(consult){
            var b = "<button id='pendingConsult' onClick='console.log("+ txt +")'>caller=" + consult.caller + " medium=" + consult.nom + " state=" + consult.state + "</button>";
            htmlIn = htmlIn + "<br>" + b;
        });
        $('#listBoxDiv').html(title + "<br>" +htmlIn);

    })
    .fail(function(error){// Appel KO => erreur technique à gérer
        console.log('Erreur:',error);// LOG sur la Console Javascript
        alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}