// HTML 문서가 로드된 후 실행
document.addEventListener('DOMContentLoaded', function () {
    // 화면에 표시된 모든 요소 숨기기
    hideElementsWithPattern('editList');
    hideElementsWithPattern('editReply');

    // 필요에 따라 다른 패턴에 대해 추가로 호출
});

function hideElementsWithPattern(pattern) {
    // 모든 요소를 가져와서 반복
    var allElements = document.querySelectorAll('[id^="' + pattern + '"]');
    allElements.forEach(function (element) {
        // 각 요소를 숨김
        element.style.display = 'none';
    });
}