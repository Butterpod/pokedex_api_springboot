const API_URL = "http://localhost:9003/api/pokedex";

async function fetchPokemons() {
  const res = await fetch(API_URL);
  const data = await res.json();
  displayPokemons(data);
}

function displayPokemons(pokemons) {
  const listEl = document.getElementById("pokemon-list");
  listEl.innerHTML = "";

  pokemons.forEach(pokemon => {
    const card = document.createElement("div");
    card.className = "pokemon-card";
    card.innerHTML = `
      <a class="card-link" href="pokemon.html?id=${pokemon.id}">
        <strong>${pokemon.name}</strong> (${pokemon.color})<br/>
        Types: ${pokemon.type1}${pokemon.type2 ? " / " + pokemon.type2 : ""}<br/>
        <div class="muted">
          <span class="stat">HP: ${pokemon.hp ?? "-"}</span>
          <span class="stat">ATK: ${pokemon.attack ?? "-"}</span>
          <span class="stat">DEF: ${pokemon.defense ?? "-"}</span>
          <span class="stat">SPD: ${pokemon.speed ?? "-"}</span>
        </div>
      </a>
    `;
    listEl.appendChild(card);
  });
}

// Chargement
fetchPokemons();