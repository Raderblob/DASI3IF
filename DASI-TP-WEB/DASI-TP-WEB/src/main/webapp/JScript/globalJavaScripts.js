/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function disconnectScript(){       
    // Extraire le numero de l'étudiant de l'URL: URL.html?numeroEtudiant=<numero>
    $.ajax({// Requête AJAX43                     
            url:'./Controleur',// URL
            method:'POST',// Méthode
            data:{// Paramètres
            todo:'invalidateSession'},
            dataType:'json'// Type de retour attendu
    })
    .done(function(response){// Appel OK => "response" contient le résultat JSON
        // Récupération des données                     
        window.location='./index.html';
    })
    .fail(function(error){// Appel KO => erreur technique à gérer
    console.log('Erreur:',error);// LOG sur la Console Javascript
    alert('Erreur lors du chargement des données: HTTP Code '+error.status);// Popup d'erreur
    });
}
