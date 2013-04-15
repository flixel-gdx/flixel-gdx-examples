package org.flixel.examples.breath;

import java.util.HashMap;
import java.util.Map;

import org.flixel.*;

import com.badlogic.gdx.utils.Json;

public class World {
    
    public String MapJSON = "examples/breath/data/world.json";
    
    private String AutoTiles = "examples/breath/data/pack:autotiles";
    
    private String WaterAutoTiles = "examples/breath/data/pack:water-autotiles";
    
    private String OctopusImage = "examples/breath/data/pack:octopus";
    
    public static int TILE_SIZE = 8;
    public FlxTilemap walls_map;
    public FlxTilemap water_map;
    public FlxTilemap safezone_map;        
    public int width;
    public int height;        

    public Map<String, FlxObject> airbubble_entrances;
    public Map<String, RestorePoint> airbubble_restore_points;

    public FlxObject darkness_init_area;
    public FlxObject endgame_area;
    public FlxSprite octopus;
    
    public Map<String, String> stories;

    public FlxGroup firefish_group;
    
    public World() {
        walls_map = new FlxTilemap();
        //walls_map.startingIndex = 0;
        //walls_map.collideIndex = 1;
        //walls_map.auto = FlxTilemap.AUTO;

        // Water map works just like walls map, but never actually
        // collide against it; just detect overlaps on it to set
        // a 'swimming' variable or the like.
        water_map = new FlxTilemap();
        //water_map.startingIndex = 0;
        //water_map.collideIndex = 1;
        //water_map.auto = FlxTilemap.AUTO;

        // In the safe zone, character has no gravity but cannot drown.
        safezone_map = new FlxTilemap();
        //safezone_map.startingIndex = 0;
        //safezone_map.collideIndex = 1;
        //safezone_map.auto = FlxTilemap.AUTO;
        
        airbubble_entrances = new HashMap<String, FlxObject>();
        airbubble_restore_points = new HashMap<String, RestorePoint>();
        
        Json jsonReader = new Json();
        jsonReader.setIgnoreUnknownFields(true);
        WorldMap map = jsonReader.fromJson(WorldMap.class, FlxG.loadString(MapJSON));

        width = map.width;
        height = map.height;

        // Tile layers
        
        for (WorldMap.Layer layer : map.layers) {
            if(layer.name.equals("walls")) {
                walls_map.loadMap(layer.tiles, AutoTiles, TILE_SIZE, TILE_SIZE, FlxTilemap.AUTO);
            } else if(layer.name.equals("water")) {
                water_map.loadMap(layer.tiles, WaterAutoTiles, TILE_SIZE, TILE_SIZE, FlxTilemap.AUTO);
            } else if(layer.name.equals("safezone")) {
                safezone_map.loadMap(layer.tiles, AutoTiles, TILE_SIZE, TILE_SIZE, FlxTilemap.AUTO);
            }
        }

        // Object groups
        for (WorldMap.ObjectGroup objectgroup : map.objectgroups) {
                if(objectgroup.name.equals("events")) {
                    for (WorldMap.Object obj : objectgroup.objects) {
              
                            String bubble_id = "";
                            String[] split = obj.name.split("-");
                            if (split.length > 1)
                            	bubble_id = split[1];

                            if(obj.type.equals("restore")) {
                                airbubble_restore_points.put(bubble_id, new RestorePoint(obj.x, obj.y));
                                if(obj.nonote) {
                                    airbubble_restore_points.get(bubble_id).note = false;
                                }
                            } else if(obj.type.equals("enter")) {
                                // Just use a generic FlxObject here since all we're doing with
                                // entrances is checking overlaps.
                                airbubble_entrances.put(bubble_id, new FlxObject(obj.x, obj.y, obj.width, obj.height));
                            } else if(obj.type.equals("darkness")) {
                                darkness_init_area = new FlxObject(obj.x, obj.y, obj.width, obj.height);
                            } else if(obj.type.equals("endgame")) {
                                endgame_area = new FlxObject(obj.x, obj.y, obj.width, obj.height);
                                octopus = new FlxSprite(obj.x + 120, obj.y + 120, OctopusImage);
                                octopus.setAlpha(0.7f);
                            }
                        }
                } else if(objectgroup.name.equals("firefish")) {
                    firefish_group = new FlxGroup();

                    for (WorldMap.Object fish_obj : objectgroup.objects) {
                        firefish_group.add(
                            new Firefish(
                                fish_obj.x - (fish_obj.x % World.TILE_SIZE),
                                fish_obj.y - (fish_obj.y % World.TILE_SIZE),
                                PlayState.world_darkness,
                                water_map,
                                walls_map
                            )
                        );
                    }
                }
        }

        // Stories
        stories = new HashMap<String,String>();

        stories.put("1", "My dear friend,\nI cannot know how long you waited before following me, but I look forward to meeting you in these underwater caves!");
        stories.put("2", "My dear friend,\nWhen I dove into that small pond, I knew it may take you some time to come along.");
        stories.put("3", "My dear friend,\nThis tunnel seems to be a dead end; I will backtrack a bit and then dive deeper downward. How deep and dark this cave must be!");
        stories.put("4", "My dear friend,\nDo you remember the small green fish we used to catch in this pond? They must prefer the shallow water, I don't see them down here.");
        stories.put("5", "My dear friend,\nIt's been hours now! I wonder if you're just behind me. I know moving through these caves must be a bit slower with your limitations...");
        stories.put("6", "My dear friend,\nOut of the corner of my eye I saw something move back here and thought it might be you; maybe you had somehow passed me! But it was just one of those glowing fish.");
        stories.put("7", "My dear friend,\nIt's so dark here. I can't see well in the dark. And you can't hold your breath for long! It will be difficult for both of us.");
        stories.put("8", "My dear friend,\nI stopped here for breakfast. I wonder: now that you're reading this, you must be quite deep in these caves. I've been here for months... Does it feel like home to you?");
        stories.put("9", "My dear friend,\nWhat an enormous, beautiful cavern! How are you stomaching the pressure? Well, I have to go now!");
        stories.put("10", "My dear friend,\nI'm sure you've noticed the strange behavior of the water... down here, water and air are always pushing each other into the strangest positions.");
        stories.put("11", "My dear friend,\nI stopped here to rest my eye and stretch. I was thinking of you and the meals we used to cook together. Soon I hope we will be making cave pies!");
        stories.put("12", "My dear friend,\nHas it really been a year? I was looking at that little leatherbound calendar you gave me and the realization just hit me. Oh how I hope you will catch up with me soon!");
        stories.put("13", "My dear friend,\nI am a bit embarrassed to tell you this: I just spent most of a week running in circles! These tunnels are so confusing. Well, time to press on.");
        stories.put("14", "My dear friend,\nThese glowing fish are a TREMENDOUS help! And also some comfort; it has been years since I jumped into that pond...");
        stories.put("15", "My dear friend,\nIt's a bit terrifying, isn't it? Such a long drop, I don't see how we could climb back. I've spent a month here already, just working up the courage...");
        stories.put("16", "My dear friend,\nThe pressure down here, what a headache! I have a tip: chew on some of the tough seaweed. I hope you find this note, some day.");
        stories.put("17", "My dear friend,\nI must be getting old; that climb took a lot out of me. I hope some day you will know what I mean.");
        stories.put("18", "My dear friend,\nWhen we were children, I dove into a small pond. Now we are both very old, and it's time for me to stop exploring. I will wait for you here in the water. But there's no need for you to hurry; I can hold my breath forever!");
        
        // old stories
        /*
        stories['0'] = 'There were two friends. One said "I Can Hold My Breath Forever," and dove into a small pond.';
        stories['1'] = 'After a while, the other friend followed.';
        stories['2'] = 'The small pond concealed a network of underwater tunnels and caves.';
        stories['3'] = 'Many of the caves and tunnels seemed to be carved out of the rock deliberately.';
        stories['4'] = 'It was so oppressively dark and close in the underwater tunnels; each cave was like an oasis despite the cold, stale air.';
        stories['5'] = "One could have easily lost one's way in these dark tunnels and never be heard of again.";
        stories['6'] = 'Who would carve tunnels so confusing and mazelike, and why?';
        stories['7'] = 'Was it really all natural?';
        stories['8'] = "Water shouldn't have behaved this way...";
        stories['9'] = 'It was a large, beautiful cavern. Surely if one were to come across this cavern they would stop and wait for their friend to share the experience...';
        stories['10'] = 'Surely it was water and not ice; but it hung in a block, solid to the eye but fluid to the touch.';
        stories['11'] = 'So deep. What could be at the bottom?';
        stories['12'] = 'The small glowing fish were welcome companions.';
        stories['13'] = 'Here deep in the cave nothing behaved as it should.';
        */
    }
    
    private static class WorldMap{
    	public int width;
    	public int height;
    	public Layer[] layers;
    	public ObjectGroup[] objectgroups;
    	//public String orientation;
    	
    	public static class Layer{
    		public String name;
    		public String tiles;
    		//public int width;
    		//public int height;
    	}
    	
    	public static class ObjectGroup{
    		public String name;
    		public Object[] objects;
    		//public int width;
    		//public int height;
    	}
    	
    	public static class Object{
    		public String name;
    		public String type;
    		public int x;
    		public int y;
    		public int width;
    		public int height;
    		public boolean nonote;
    	}
    }
}