# 🔒 Formulaire « Sécurité des données » — réponses à saisir dans la Play Console

*Play Console → Règles → Contenu de l'application → Sécurité des données*

---

## Section 1 — Collecte et partage de données

| Question | Réponse |
|---|---|
| Votre application collecte-t-elle ou partage-t-elle des types de données utilisateur obligatoires ? | **NON** |
| Toutes les données utilisateur collectées par votre application sont-elles chiffrées en transit ? | *(sans objet — aucune donnée transmise)* |
| Proposez-vous un moyen aux utilisateurs de demander la suppression de leurs données ? | *(sans objet — aucune donnée collectée)* |

> ⚠️ **Important** : le stockage local (localStorage) de la progression du jeu **ne compte pas**
> comme une collecte de données au sens de Google Play, car l'information ne quitte jamais
> l'appareil et n'est transmise à personne. Vous répondez donc bien **NON** à la première question.

---

## Section 2 — Détail par catégorie (aucune case à cocher)

| Catégorie | Collectée | Partagée |
|---|---|---|
| Position | ❌ | ❌ |
| Informations personnelles (nom, e-mail, adresse, identifiants) | ❌ | ❌ |
| Informations financières | ❌ | ❌ |
| Santé et remise en forme | ❌ | ❌ |
| Messages | ❌ | ❌ |
| Photos et vidéos | ❌ | ❌ |
| Fichiers audio | ❌ | ❌ |
| Fichiers et documents | ❌ | ❌ |
| Agenda | ❌ | ❌ |
| Contacts | ❌ | ❌ |
| Activité dans l'application | ❌ | ❌ |
| Recherche sur le Web | ❌ | ❌ |
| Identifiants de l'appareil ou autres | ❌ | ❌ |
| Achats dans l'application | ❌ | ❌ |
| Journaux de plantage / diagnostics | ❌ | ❌ |

---

## Section 3 — Pratiques de sécurité

| Question | Réponse |
|---|---|
| Les données sont-elles chiffrées en transit ? | Sans objet |
| L'utilisateur peut-il demander la suppression de ses données ? | Sans objet |
| Votre application a-t-elle fait l'objet d'un examen de sécurité indépendant ? | Non |

---

## Section 4 — Justification (à conserver en interne)

L'application « Super Dentiste » :
- ne contient **aucun SDK tiers** (pas de Firebase, pas de Google Analytics, pas de régie publicitaire) ;
- ne demande **aucune permission dangereuse** — seulement `INTERNET` (pour ouvrir un lien de
  rendez-vous dans le navigateur externe, à la demande explicite de l'utilisateur) et `VIBRATE`
  (retour haptique du jeu) ;
- stocke uniquement la progression de jeu dans le `localStorage` de la WebView, sur l'appareil,
  supprimé avec l'application ;
- ne propose ni compte utilisateur, ni achat intégré, ni publicité.

---

## Section 5 — Programme Familles (public incluant les enfants)

Puisque le public cible inclut les moins de 13 ans, l'application doit respecter en permanence :

- ✅ Aucune publicité (ni bannière, ni interstitiel, ni vidéo récompensée)
- ✅ Aucun achat intégré
- ✅ Aucune collecte d'identifiant publicitaire (AAID)
- ✅ Aucun SDK de mesure d'audience non certifié « Familles »
- ✅ Aucun lien de réseau social intégré
- ✅ Contenu adapté : aucune scène effrayante, aucun sang, aucune douleur représentée
- ✅ Les liens sortants (site du cabinet, appel téléphonique) s'ouvrent hors de l'application

> 🚨 **À retenir pour les futures mises à jour** : ajouter une régie publicitaire ou Firebase
> Analytics ferait basculer l'application hors du programme Familles et exigerait une nouvelle
> déclaration complète. Le modèle « zéro donnée » est aussi le plus simple à maintenir.
