# take five

**take five** is a daily Android journaling app, built around simple rituals.

You **take five** minutes — in the morning and at night — to reflect, reset, and keep track of how things are going.

---

### 🌱 why i built it

I wanted something calm, offline, and unintrusive — no insights, no pushy streaks, no cloud dependency with simple UI and solid architecture.

I also wanted it to feel like my real gratitude journal. One that lives only on your device — no syncing, no backup, no login. If you delete the app, it’s like tossing out a notebook. And once you write something, you can’t go back and edit it later — like you wrote it with a pen.

---

## 🧭 features

- 🌄 **morning prompts** – what you're grateful for & what would make today great
- 🌙 **evening prompts** – what went well & what you could've done better
- 🗓️ **archive** – calendar view to see which days you’ve journaled & read old entries
---

## 🧰 tech stack & architecture


| Layer | Tools                                                                    |
|-------|--------------------------------------------------------------------------|
| UI | Compose (Material 3), Compose Navigation                                 |
| State | ViewModel, UiState + StateFlow                                           |
| Data | Room (DAO, Database, Entity)                                             |
| DI | Hilt                                                                     |
| Domain | UseCases, Repository pattern                                             |
| Async | Kotlin Coroutines + Flow                                                 |
| Misc | this amazing [Calendar](https://github.com/kizitonwose/Calendar) library |
> 🧠 I was intentionally experimenting with clear modern Android architecture layers (UI, domain, data) for maintainability.
> 
> Most calls that touch the database goes through a UseCase for fun. Repository calls the DAO directly (no DataSource layer).

---
<!--

## 📸 sneak peek

```bash
# ./screenshots/morning.png     ./screenshots/evening.png     ./screenshots/calendar.png
```
<p float="left">
  <img src="./screenshots/morning.png" width="200" />
  <img src="./screenshots/evening.png" width="200" />
  <img src="./screenshots/calendar.png" width="200" />
</p>

---
## 🏁 getting started

```bash
git clone https://github.com/yourusername/take-five.git
cd take-five
./gradlew installDebug
```

Open in Android Studio and run it on your device or emulator.

---
-->

## 🛣️ roadmap

- [ ] Gentle notification reminders (optional)
- [ ] Custom day boundaries — set your “day end” time (e.g., 4 AM instead of midnight) to better fit late-night routines
- [ ] Better tablet + foldable UI

---

## 🤝 open to feedback

I built **take five** just for myself, but it’s meant to be used by real people. If you have ideas or want to contribute - feel free to reach out!
