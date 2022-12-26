(async () => {
	window.addEventListener("show", showApp);
	function showApp(event) {
		window.removeEventListener("show", showApp);
		
		document.querySelector("#app").style.display = "block";
	}
	
	let res = await fetch(
		"http://localhost:3000/user/check/",
		{
			credentials: "include",
			method: "GET",
			headers: {
				"Accept": "application/json",
				"Content-Type": "application/json",
			},
		}
	);
	let json = await res.json();
	if (json.username) {
		window.dispatchEvent(new CustomEvent("login", {detail: {
			login: true,
			username: json.username,
			support: json.support,
		}}));
	} else {
		window.dispatchEvent(new CustomEvent("login", {detail: {
			login: false,
		}}));
	}
})();