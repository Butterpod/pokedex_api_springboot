const API_URL = "http://localhost:9003/api/pokedex";

async function testFetch() {
  try {
    const res = await fetch(API_URL);

    console.log("Print Res :",res); // affiche tout le JSON
    const data = await res.json();
    console.log("Pokédex récupéré :", data); // affiche tout le JSON
    //console.log("Premier Pokémon :", data[0]); // affiche juste le premier
    //console.log("Nom du premier Pokémon :", data[0].name); // affiche son nom
    } catch (e) {
        console.error("Erreur lors du fetch :", e);
      }

      }
      /*
      const res = await fetch(API_URL);

          console.log("Print Res :", res); // objet Response
          const data = await res.json();
          console.log("Pokédex récupéré :", data); // tableau JSON

          // Simulons une URL avec query string
          const fakeUrl = "http://localhost:9003/pokemon.html?id=25&type=plante";
          const queryString = fakeUrl.split("?")[1]; // "id=25&type=plante"
          const params = new URLSearchParams("?" + queryString);

          console.log("Params object:", params);
          console.log("id:", params.get("id"));       // "25"
          console.log("type:", params.get("type"));   // "plante");

        } catch (e) {
          console.error("Erreur lors du fetch :", e);
        }
      */



testFetch();
