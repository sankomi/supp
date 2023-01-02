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
		const hello = document.querySelector("#hello");
		if (hello) hello.textContent = "hello, " + json.username + "!";
		document.querySelector("#login")?.remove();
		window.dispatchEvent(new CustomEvent("login", {detail: {
			login: true,
			username: json.username,
			support: json.support,
		}}));
	} else {
		document.querySelector("#logout")?.remove();
		document.querySelector("#change")?.remove();
		document.querySelector("#home")?.remove();
		window.dispatchEvent(new CustomEvent("login", {detail: {
			login: false,
		}}));
	}
	if (!json.support) document.querySelector("#users").remove();
	
	const logout = document.querySelector("#logout");
	if (logout) logout.addEventListener("click", async event => {
		event.preventDefault();
		let res = await fetch(
			"http://localhost:3000/user/logout/",
			{
				credentials: "include",
				method: "DELETE",
				headers: {
					"Accept": "application/json",
					"Content-Type": "application/json",
				},
			}
		);
		let json = await res.json();
		if (json.result === "success") window.location.href = "/login/";
	});
})();
