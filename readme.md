#replays
##todo
convert replay to HR by inverting y values etc. (close to done)

##format
* byte - game mode
* int32 - file version
* string - beatmap md5 hash
* string - username
* string - replay hash
* uint16 - number of 300 hits
* uint16 - number of 100 hits
* uint16 - number of 50 hits
* uint16 - number of 300 beats
* uint16 - number of 100 beats
* uint16 - number of misses
* uint32 - points (total score)
* uint16 - max combo
* boolean - full combo / perfect
* int32 - mods (to combine mods, do x | y {e.g hdhr would be 16 | 8})
* string - diagram
* date - timestamp
* byte[] - compressed replay data (compressed with lzma)
* int32 - online score ID (only if file version >= 20121008)