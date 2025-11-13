
function updateStats() {
    const fpsEl = document.getElementById("fps");
    const resEl = document.getElementById("res");
    // dummy values
    fpsEl!.textContent = "N/A";
    const img = document.getElementById("frame") as HTMLImageElement;
    if (img && img.naturalWidth) {
        resEl!.textContent = img.naturalWidth + "x" + img.naturalHeight;
    } else {
        resEl!.textContent = "-";
    }
}

window.addEventListener("load", () => {
    updateStats();
});
