package bjfu.it.huangyichao.firesafety;


import androidx.annotation.NonNull;

public class Fire {
    private String name;
    private String description;
    private int imageSourceId;

    public static final Fire[] fires = {
            new Fire("AodaliyaFire","Fire is bad!", R.drawable.aodaliya),
            new Fire("SichuanFire","Firefighter is brave!",R.drawable.sichuan),
            new Fire("NeimengguFire","Hope we will be okay!",R.drawable.neimenggu)
    };

    public String getName() {
        return name;
    }

    public Fire(String name, String description, int imageSourceId) {
        this.name = name;
        this.description = description;
        this.imageSourceId = imageSourceId;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageSourceId() {
        return imageSourceId;
    }


}
