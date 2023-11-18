const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const card = urlParams.get('card');

console.log("card", card);

document.getElementById("textStudyBotton").addEventListener("click", function(event) {
            const card1 = card;
            console.log("card1", card1);
            redirectToTextLearningPage(card1);
    //        event.preventDefault();
    });

function redirectToTextLearningPage() {
                console.log("redirecting with card", card);
                const apiUrl = `/quizhome?card=${card}`;
                window.location.href = apiUrl;
            }