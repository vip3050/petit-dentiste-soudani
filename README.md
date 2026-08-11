# 🦷 Super Dentiste

Jeu mobile officiel du **[Cabinet Soudani](https://cabinetsoudani.com)** (Drs Keirless, depuis 1996).

Deviens dentiste avec **Dr Nagib** et **Dr Ihab** : **16 mini-jeux**, **40 niveaux**, en français et en arabe.

▶️ **Jouer maintenant : https://vip3050.github.io/petit-dentiste-soudani/**

---

## 📦 Contenu du dépôt

| Dossier | Contenu |
|---|---|
| `index.html` | Le jeu complet — un seul fichier autonome, aucune dépendance |
| `android/` | Projet **Android Studio** prêt à compiler pour le Play Store |
| `playstore/` | Éléments de publication : descriptions, guide, sécurité des données |
| `privacy.html` | Politique de confidentialité (hébergée par GitHub Pages) |
| `shots/` | Variante du jeu servant à produire les captures haute résolution |

---

## 🎮 Le jeu

### 16 mini-jeux
🪥 Détartrage · 🌀 Caries (turbine + spatule) · 👑 Couronne zircone · 🔩 Implant titane
(forage → vissage → couronne) · 🦠 Chasse aux microbes · ✨ Brossage éclat · 📸 Radio panoramique ·
😬 Orthodontie · 🍬 Pluie de bonbons · 🧵 Fil dentaire · 💡 Blanchiment laser · 🫧 Bain de bouche ·
🧠 Memory · 🧺 Attrape les dents de lait · 🎈 Bulles magiques · ❓ Quiz du docteur

### Autres fonctionnalités
- 40 niveaux, 6 patients avec leur petite histoire, difficulté progressive
- 2 dentistes jouables, grades de Stagiaire à Implantologue Expert
- Bilingue français / العربية (RTL complet)
- Sons, vibrations, confettis, sauvegarde automatique
- Sans publicité, sans achat intégré, fonctionne hors ligne
- Bouton « Prendre un vrai rendez-vous » vers le cabinet

---

## 📱 Application Android

Le dossier `android/` contient un projet Android Studio complet et prêt à compiler
(WebView native plein écran, splash screen, icône adaptative, signature de release).

```bash
cd android
./gradlew bundleRelease
```

📖 Procédure complète de publication : `playstore/GUIDE-PUBLICATION.md`

Politique de confidentialité en ligne :
https://vip3050.github.io/petit-dentiste-soudani/privacy.html

---

© Cabinet Soudani — jeu de divertissement et de sensibilisation à l'hygiène bucco-dentaire.
Ne remplace pas une consultation chez un chirurgien-dentiste.
