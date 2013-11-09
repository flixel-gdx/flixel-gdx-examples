package org.flixel.examples.breath;

import org.flixel.FlxG;
import org.flixel.FlxGroup;
import org.flixel.FlxObject;
import org.flixel.FlxPoint;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;
import org.flixel.event.IFlxCamera;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class PlayState extends FlxState {
    
    //private String AutoTiles = "examples/breath/data/pack:autotiles";
    
    private String BackgroundImage = "examples/breath/data/pack:world-background";

    public String GameMusic = "examples/breath/data/breath-mallets-128.mp3";
    
    public String RevNoiseSound = "examples/breath/data/rev-noise.mp3";
    
    private String GardeniaFont = "examples/breath/data/gardenia.ttf";
        
    private World world;
    private Player player;

    private String saved_restore_point = "1";

    private float oxygen_timer = 10.0f;
    private FlxText oxygen_timer_display;
    private FlxSprite darkness;

    private int darkness_color = 0xee000000;
    public static FlxSprite world_darkness;
    
    private boolean player_dead = false;
    private float player_death_length = 1.0f;
    private float player_death_timer = 0.0f;

    private StoryOverlay story_overlay;
    private FlxGroup notes;

    private FlxSprite background;
    private boolean won_done = false;

    private FlxText title_text;
    //private FlxButton start_button;
        
    private boolean game_started = false;

    private boolean cheats = true;
    
    public static FrameBuffer frame_buffer;
    
    public FlxSprite frame_buffer_fill;
        
    @Override 
    public void create() {
    	FlxG.setBgColor(Breath.bgcolor);
    	
    	frame_buffer = new FrameBuffer(Pixmap.Format.RGBA8888, FlxG.width, FlxG.height, true);
    	
        title_text = new FlxText(4, 24, 290, "\"I Can Hold My Breath Forever\"\nUse arrow keys to move.");
        title_text.setFormat(GardeniaFont, 8, 0xffffffff);
            
        PlayState.world_darkness = new FlxSprite(0,0);
        world_darkness.scrollFactor.x = world_darkness.scrollFactor.y = 0;
        world_darkness.setPixels(new AtlasRegion(frame_buffer.getColorBufferTexture(), 0, 0, frame_buffer.getWidth(), frame_buffer.getHeight()));
        world_darkness.framePixels.flip(false, true);
        world_darkness.blend = "multiply";
        world_darkness.setAlpha(0);
        world_darkness.visible = false;
        
        frame_buffer_fill = new FlxSprite(0,0);
        frame_buffer_fill.makeGraphic(FlxG.width, FlxG.height, darkness_color);
        frame_buffer_fill.scrollFactor.x = frame_buffer_fill.scrollFactor.y = 0;
        
        world = new World();

        background = new FlxSprite(0,0,BackgroundImage);
        
        // Add restore point sprites
        notes = new FlxGroup();
        for (RestorePoint note : world.airbubble_restore_points.values()) {
            if(note.note) {
                notes.add(note);
            }
        }
            
        player = new Player(4 * World.TILE_SIZE, 9 * World.TILE_SIZE, world_darkness);
            
        darkness = new FlxSprite(0, 0);
        darkness.makeGraphic(FlxG.width, FlxG.height, 0xff000000);
        darkness.scrollFactor.x = darkness.scrollFactor.y = 0;
        darkness.setAlpha(0.0f);
            
        oxygen_timer_display = new FlxText(0, 0, FlxG.width, "10");
        oxygen_timer_display.setFormat(null, 160, 0xffffff, "center");
        oxygen_timer_display.setAlpha(0.0f);
        oxygen_timer_display.scrollFactor.x = oxygen_timer_display.scrollFactor.y = 0;
           
        story_overlay = new StoryOverlay(8, 2);
           
        world.walls_map.follow();
        //FlxG.followAdjust(0.5, 0.5);
        //FlxG.follow(player, 2.5);
        FlxG.camera.follow(player);
        
        //this.add(world.walls_map);
        //this.add(world.water_map);
        this.add(background);
        this.add(world.firefish_group);
        this.add(world.octopus);
        this.add(notes);
        this.add(player);
        this.add(world_darkness);
        this.add(darkness);
        this.add(oxygen_timer_display);
        this.add(story_overlay);
        this.add(title_text);
    }

    // For testing, skip ahead to the next restore point.
    public void skip_ahead() {
        String next_point = Integer.toString(Integer.parseInt(saved_restore_point) + 1);

        if(!world.airbubble_restore_points.containsKey(next_point)) {
            next_point = "0";
        }
            
        RestorePoint restore_point = world.airbubble_restore_points.get(next_point);
        saved_restore_point = next_point;
                
        player.x = restore_point.x;
        player.y = restore_point.y;

    }
        
    @Override 
    public void update() {
        // CHEAT CODE LOL
        if(cheats) {
            if(FlxG.keys.justPressed("N")) {
                skip_ahead();
                super.update();
                return;
            }
        }

        if(player.won_game) {
            oxygen_timer_display.setAlpha(0);
            darkness.setAlpha(0);

            int speed = 400;
            FlxPoint target = new FlxPoint(world.octopus.x - 16, world.octopus.y + 4);

            if(player.x < target.x) {
                player.velocity.x += speed * FlxG.elapsed;
            } else if(player.x > target.x) {
                player.velocity.x -= speed * FlxG.elapsed;
            }

            if(player.y > target.y) {
                player.velocity.y -= speed * FlxG.elapsed;
            } else if(player.y < target.y) {
                player.velocity.y += speed * FlxG.elapsed;
            }

            if(Math.abs(player.y - target.y) < 16 &&
                Math.abs(player.x - target.x) <= 16 &&
                !won_done) {
                player.setFacing(FlxSprite.RIGHT);
                won_done = true;
                    
                FlxG.fade(0xff000000, 5, new IFlxCamera() {

					@Override
					public void callback() {
						FlxG.switchState(new EndGameState());
					}
                        
                    });
            }
                
            super.update();
            return;
        }
            
        if(player_dead) {
            player.active = false;
               
            player_death_timer -= FlxG.elapsed;
                
            if(player_death_timer <= 0.0) {
                player_dead = false;
                player.active = true;
            }

            super.update();
            return;
        }
        
        FlxG.collide(player, world.walls_map);
        FlxG.collide(world.firefish_group, world.walls_map, Firefish.callback);
        
        if(world.water_map.overlaps(player)) {
            player.in_water = true;
            player.glow.scale.x = player.glow.scale.y = 4;
        } else {
            player.in_water = false;
            player.glow.scale.x = player.glow.scale.y = 8;                
        }

        if(world.safezone_map.overlaps(player) || world.water_map.overlaps(player)) {
            player.gravity_on = false;
        } else {
            player.gravity_on = true;
        }

        // Nudge the player up if they're in the safe zone.
        if(world.safezone_map.overlaps(player)) {
            player.push_up = true;
        } else {
            player.push_up = false;                
        }

        // Check air bubble entrances
        for(String bubble_id : world.airbubble_entrances.keySet()) {
            FlxObject air_bubble_entrance = world.airbubble_entrances.get(bubble_id);
                if(air_bubble_entrance.overlaps(player)) {
                    if(bubble_id != saved_restore_point) {
                        saved_restore_point = bubble_id;
                    }
                }
        }

        // Check story points
        for(String restore_point_id : world.airbubble_restore_points.keySet()) {
            RestorePoint restore_point = world.airbubble_restore_points.get(restore_point_id);
                
            if(player.overlaps(restore_point)) {
                if(world.stories.containsKey(restore_point_id)) {
                    story_overlay.showText(world.stories.get(restore_point_id));
                }
            }
        }
            
        // Check oxygen level & update label
        if(player.in_water) {
            oxygen_timer_display.setText(Integer.toString((int)(Math.ceil(oxygen_timer))));
            oxygen_timer_display.setAlpha(1.0f - (oxygen_timer / 10.0f));
                
            darkness.setAlpha((float) Math.pow(1.0f - (oxygen_timer / 10.0f), 2));

            float max_overlay_alpha = 0.9f;
               
            if(darkness.getAlpha() > max_overlay_alpha) {
                darkness.setAlpha(max_overlay_alpha);
            }

            if(oxygen_timer_display.getAlpha() > max_overlay_alpha) {
               oxygen_timer_display.setAlpha(max_overlay_alpha);
            }
                
            oxygen_timer -= FlxG.elapsed;

            if(oxygen_timer < 0.0f) {
                oxygen_timer = 0.0f;
                kill_player();
            }
        } else {
            oxygen_timer = 10.0f;
            darkness.setAlpha(0);
            oxygen_timer_display.setAlpha(0);
        }

        // Start music
        if(!game_started && player.overlaps(world.darkness_init_area)) {
            game_started = true;
                
            FlxG.playMusic(GameMusic);
        }
            
        // World darkness init and kill titles (when the player dives into the pond);
        if(world_darkness.getAlpha() < 1 && (world_darkness.getAlpha() > 0 || player.overlaps(world.darkness_init_area))) {
            world_darkness.visible = true;
        	world_darkness.setAlpha(world_darkness.getAlpha() + FlxG.elapsed);
            title_text.setAlpha(title_text.getAlpha() - FlxG.elapsed);
        }
            
        // Endgame init
        if(player.overlaps(world.endgame_area)) {
            player.won_game = true;
        }
            
        super.update();
    }

    @Override 
    public void draw() {
        //world_darkness.fill(darkness_color);
    	
    	FlxG.batch.end();
    	frame_buffer.begin();
        FlxG.batch.begin();
        frame_buffer_fill.draw();
        player.drawGlow();
        world.firefish_group.callAll("drawGlow");
    	FlxG.batch.end();
    	frame_buffer.end();
    	FlxG.batch.begin();
    	
        super.draw();
    }
        
    private void kill_player() {
        // The player has drowned; move them back to the last restore point
        RestorePoint restore_point = world.airbubble_restore_points.get(saved_restore_point);
            
        player.x = restore_point.x;
        player.y = restore_point.y;
        player.velocity.x = player.velocity.y = 0;
            
        // Re-set the 'death' timer which holds the blackout & countdown
        // for a couple seconds.
        player_dead = true;
        player_death_timer = player_death_length;

        // Complete blackout
        oxygen_timer_display.setText("0");
        darkness.setAlpha(1.0f);
        oxygen_timer_display.setAlpha(1.0f);

        // Play sound effect
        FlxG.play(RevNoiseSound);
    }
    
    @Override
    public void destroy() {
    	frame_buffer.dispose();
    }
}