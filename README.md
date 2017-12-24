[![Codacy Badge](https://api.codacy.com/project/badge/Grade/92e1f0f56b3e465f88a20fc452ae8c6d)](https://www.codacy.com/app/tuan3-nguyen/sw-bot?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=justindannguyen/sw-bot&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/justindannguyen/sw-bot/branch/master/graph/badge.svg)](https://codecov.io/gh/justindannguyen/sw-bot) 
[![Build Status](https://travis-ci.org/justindannguyen/sw-bot.svg?branch=master)](https://travis-ci.org/justindannguyen/sw-bot)
# sw-bot
### Introduction
This is the fun project and use it as your own risk. We will not responsible for any bane or account lock.

### Technologies stack
| Name  | Version | Description |
| ----- |---------|-------------|
| Java  | 1.8     | Java is being used to have the basic logic flow and Swing is being used to develop the UI.|
| JavaCV| 1.3.3     | To detect the game status, we use [javacv](https://github.com/bytedeco/javacv) on top of opencv|
| ADB   | -       | Communication with phone and have click command.|
| Maven | -       | Build platform|
| Eclipse | Phonton | Development IDE|

### How to use?
+ Create configuration profile for your phone e.g. location, game picture.
+ Connect phone with PC.
+ Select profile & dungeon type then click start.
+ Enjoy your life!!!

### Roadmap
- [x] UI for screen location & image configuration.
- [x] Elemental rift dungeon farming. 
- [x] Rune farming in dungeon e.g. DB10, GB10.
- [x] Auto refill energy with number of time configuration.
- [x] Retry if network delay or network unstable.
- [ ] Food monster farming to upgrade 6*.
- [ ] Rune filtering rule e.g. sell all rune except 6* or legend, etc.
- [ ] Rift raid farming.

