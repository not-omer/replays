#replays
##todo
figure out a way to write BinaryWriter strings in Java  
convert replay to HR by inverting y values etc.

##format
ScoreDatabase.cs

* byte - game mode
* int32 - file version
* string - beatmap md5
* string - username
* string - hash, described below
* uint16 - number of 300 hits
* uint16 - number of 100 hits
* uint16 - number of 50 hits
* uint16 - number of 300 beats
* uint16 - number of 100 beats
* uint16 - number of misses
* uint32 - points (total score)
* uint16 - max combo
* boolean - dummy value? unsure
* int32 - mods (described below) (to combine mods, do x | y {e.g hdhr would be 16 | 8})
* string - diagram (unsure what this is)
* date - timestamp
* byte[] - compressed replay data (7zip compressed data described below)
* int32 - online score ID (only if file version >= 20121008)








```string hash = string.Format("{0}p{1}o{2}o{3}t{4}a{5}r{6}e{7}y{8}o{9}u{10}{11}{12}", new object[] { Hit100 + 
Hit300, Hit50, Hit300Beat, Hit100Beat, Hit0, BeatmapMd5, maxCombo, bool_4, UserName, Points, GetRanking(), (int) _property<Mods>.Get(Mods), bool_2 });```



    public enum Mods
    {  
        None = 0,
        NoFail = 1,  
        Easy = 2,
        Hidden = 8,
        HardRock = 16,
        SuddenDeath = 32,
        DoubleTime = 64,
        Relax = 128,
        HalfTime = 256,
        Nightcore = 512,
        Flashlight = 1024,
        Autoplay = 2048,
        SpunOut = 4096,
        Relax2 = 8192,
        Perfect = 16384,
        Key4 = 32768,
        Key5 = 65536,
        Key6 = 131072,
        Key7 = 262144,
        Key8 = 524288,
        keyMod = 1015808,
        FadeIn = 1048576,
        Random = 2097152,
        LastMod = 4194304,
        FreeModAllowed = 2077883
    }  


    public string method_4() {
        var stringBuilder = new StringBuilder();
        if (ReplayHits != null) {
            var @class = new ReplayElement(0, 0f, 0f, pButtonState.None);
            foreach (ReplayElement current in ReplayHits) {
                stringBuilder.AppendFormat("{0}|{1}|{2}|{3},", new object[]
                {
                    current.Offset - @class.Offset,
                    current.X.ToString(Osu.NumberFormat),
                    current.Y.ToString(Osu.NumberFormat),
                        (int) Class707.smethod_27(current.Left1Down, current.Left2Down, current.Right1Down, current.Right2Down)
                });
                @class = current;
                }
        }
        stringBuilder.AppendFormat("{0}|{1}|{2}|{3},", new object[]
        {
            -12345,
            0,
            0,
            int_6
        });
        return stringBuilder.ToString();
    }