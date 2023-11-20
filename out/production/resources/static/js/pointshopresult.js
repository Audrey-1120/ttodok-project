const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const selectProductCode = urlParams.get('selectProductCode');
const currentUser = urlParams.get('currentUser');

console.log(selectProductCode);
console.log(currentUser);


$.ajax({
    type: 'POST',
    url: '/pointshopresult/gifticonimg',
    data: { loginId: currentUser, productCode: selectProductCode },
    dataType: 'json',
    success: function (data) {

        console.log(data);
        updatePageWithData(data.pointEntityList);
        updateMemberPoint(data.memberPoint);
    },
    error: function (jqXHR, textStatus, errorThrown) {
        console.error('Error updatingProduct points:', errorThrown);
    }
});

function updatePageWithData(data) {

    const container = document.querySelector('.gifticon_list');
        container.innerHTML = '';

        data.forEach(pointEntity => {
            const gifticonDiv = document.createElement('div');
            gifticonDiv.classList.add('set_gifticon');

            gifticonDiv.innerHTML = `
                <div class="gifticon1">
                    <div class="gifticon_code">${pointEntity.productCode}</div>
                    <div class="gifticon_title">${pointEntity.productName}</div>
                    <img id="giftiImage" class="gifticon_img" src="../images/${pointEntity.productImage}" title="구매한 상품">
                    <button id="savebtn">저장하기</button>
                </div>
            `;

            container.appendChild(gifticonDiv);
        });

}

function updateMemberPoint(memberPoint) {
    // 멤버 포인트 업데이트
    const pointViewElement = document.getElementById('overlay-text-pointview');
    pointViewElement.textContent = memberPoint + '점';
}







//사진 저장하기....기프티콘 - 이건 브라우저 뜨자마자 바로 저장됨. 그런데 사용할 수 없는 이미지라고 뜸..
function onImageLoad() {
    var imgElement = document.getElementById('giftiImage');
    var downloadLink = document.createElement('a');
    downloadLink.href = imgElement.src;
    downloadLink.download = 'downloaded_image.png';

    document.body.appendChild(downloadLink);
    downloadLink.click();

    document.body.removeChild(downloadLink);
}

// 이미지가 로드될 때 onImageLoad 함수를 호출
document.getElementById('giftiImage').addEventListener('load', onImageLoad);

// 이미지 로드가 실패한 경우에도 onImageLoad 함수를 호출 (선택 사항)
document.getElementById('giftiImage').addEventListener('error', onImageLoad);

// 다운로드 함수
function triggerDownload() {
    // 이미지가 로드되었는지 확인
    if (document.getElementById('giftiImage').complete) {
        onImageLoad();
    } else {
        alert('이미지가 아직 로드되지 않았습니다.');
    }
}






//function triggerDownload() {
//    var imgElement = document.getElementById('giftiImage');
//    var downloadLink = document.createElement('a');
//
//    // 이미지가 로드되었을 때 실행되는 함수
//    downloadLink.href = imgElement.src;
//    downloadLink.download = 'downloaded_image.png';
//
//    document.body.appendChild(downloadLink);
//    downloadLink.click();
//
//    document.body.removeChild(downloadLink);
//}



