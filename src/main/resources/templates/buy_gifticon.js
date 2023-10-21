let userPoints = 100;

// 초기 포인트 표시
function displayPoints() {
    const pointsSpan = document.getElementById("total_points"); //수정필요
    pointsSpan.textContent = userPoints;
}

// 아이템 구매 함수
function buyItem(itemName, itemCost) {
    if (userPoints >= itemCost) {
        userPoints -= itemCost;
        alert(`아이템 "${itemName}"을 구매했습니다!`);
        displayPoints();
    } else {
        alert("포인트가 부족합니다.");
    }
}

// 초기화
displayPoints();