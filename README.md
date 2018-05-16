# OXgame project

`version 1.2.0-SNAPSHOT`

### running:
```bash
./run.sh
```

Demos available in the scenarios folder. To run demos, run setup.sh and select options:
* input=file
* input_file=scenarios/*option*
* language=en
where option is one of the following:
	* draw
	* broken (game broken in the middle with the use of quit
	* *firstWin* (full game, first player winning)


### setup:
(editing file src/main/resources/edu/tseidler/settings.properties)
```bash
./setup.sh
```
# *configuration file and options*
1. Input setup: _input_
	* stdin (standard input - keyboard)
	* file (file; when using set *input_file* option)
2. Output setup: _ouput_
	* stdout (standard ouptut - console)
	* stderr (standard error output - to console)
	* file (file; when using set *output_file* option)
3. Language setup: _language_
	*en (English)
	*pl (Polish)
4. Player setup:
	* _player1_name_ (player 1 name)
	* _player1_mark_ (mark for player 1: X or O)
	* _player1_first_ (decision if player 1 starts: true or false)
	* _player2_name_ (player 2 name)
5. board setup:
	* _board_rows_ (number of rows, minimum 3)
	* board_cols (number of cols, minimum 3)
	* board_winn (number of winning marks in line, minimum 3, not larger than any of the board dimensions)

