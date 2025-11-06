


const API = "/api/pokedex"; // même domaine/port, car on sert via Spring Boot

// Palettes  types
const TYPE_COLORS = {
  plante: "#7AC74C",
  feu: "#EE8130",
  eau: "#6390F0",
  electrik: "#F7D02C",
  fee: "#D685AD",
  insecte : "#95C22E"

};

// enlève les accents et met en minuscule
function norm(s) {
  return (s || "")
    .toString()
    .normalize("NFD")
    .replace(/\p{Diacritic}/gu, "")
    .toLowerCase()
    .trim();
}
async function loadCry(pokemonId) {
  try {
    const res = await fetch(`/api/pokedex/${pokemonId}/cry`);
    if (!res.ok || res.status === 204) return; // pas de cri
    const { url } = await res.json();

    const box = document.querySelector(".imgbox");
    const audio = document.createElement("audio");
    audio.controls = true;
    audio.src = url;
    audio.style.marginTop = "12px";
    box.appendChild(audio);
  } catch (e) {
    console.warn("Pas de cri disponible", e);
  }
}



// Récupère l'id dans l'URL
const params = new URLSearchParams(location.search);
const id = params.get("id");

if (!id) {
  document.body.innerHTML = "<p style='padding:24px'>Aucun identifiant fourni. T'as oublier de mettre un numéro aprés le idid=1 :)</p>";
  throw new Error("Missing id");
}

(async function run() {
  try {
    const res = await fetch(`${API}/${id}`); // si existe alors -> 200
    if (!res.ok) throw new Error(`API ${res.status}`);
    const p = await res.json();

    // Remplir le contenu
    document.title = `${p.name} · Pokedex`;
    document.getElementById("name").textContent = p.name;
    document.getElementById("color").textContent = `Couleur : ${p.color}`;

    // Types (chips)
    const typesEl = document.getElementById("types");
    [p.type1, p.type2].filter(Boolean).forEach(t => {
      const chip = document.createElement("span");
      chip.className = "chip";
      chip.textContent = t;
      typesEl.appendChild(chip);
    });

    // Stats
    const statsEl = document.getElementById("stats");
    const stats = [
      ["HP", p.hp], ["ATK", p.attack], ["DEF", p.defense], ["SPD", p.speed],
    ];
    stats.forEach(([label, val]) => {
      const s = document.createElement("div");
      s.className = "stat";
      s.textContent = `${label} ${val ?? "-"}`;
      statsEl.appendChild(s);
    });

    // Couleur de fond selon type1
    const key = norm(p.type1);
    const color = TYPE_COLORS[key] || "#e9ecef";

    // Linear Gradiant
    document.body.style.setProperty("--bg", `linear-gradient(90deg, ${color} 0%, #ffffff 65%)`);


    const base = "https://www.media.pokekalos.fr/img/pokemon/pokego";


    const removeAccents = str =>
      str.normalize('NFD').replace(/[\u0300-\u036f]/g, '');


    const p_name = p.name.toLowerCase();
    const pokemon_name=p_name.normalize("NFD").replace(/[\u0300-\u036f]/g, '')


    const img = document.getElementById("sprite");
    img.src = `${base}/${pokemon_name}.png`;

    img.alt = `Sprite de ${p.name}`;
    loadCry(p.id);



  } catch (e) {
    console.error(e);
    document.body.innerHTML = "<p style='padding:24px'>Impossible de charger le Pokémon. Le pokemon n'a pas encore été ajouté à la BDD</p>";
  }
})();
