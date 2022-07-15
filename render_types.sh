#!/bin/bash

MOD_ID='fantasyfurniture'
MODELS_PATH="assets/${MOD_ID}/models/block"

SOLID='solid'
CUTOUT_MIPPED='cutout_mipped'
CUTOUT='cutout'
TRANSLUCENT='translucent'
TRANSLUCENT_MOVING_BLOCK='translucent_moving_block'
TRANSLUCENT_NO_CRUMBLING='translucent_no_crumbling'
LEASH='leash'
WATER_MASK='water_mask'
ARMOR_GLINT='armor_glint'
ARMOR_ENTITY_GLINT='armor_entity_glint'
GLINT_TRANSLUCENT='glint_translucent'
GLINT='glint'
GLINT_DIRECT='glint_direct'
ENTITY_GLINT='entity_glint'
ENTITY_GLINT_DIRECT='entity_glint_direct'
LIGHTNING='lightning'
TRIPWIRE='tripwire'
END_PORTAL='end_portal'
END_GATEWAY='end_gateway'
LINES='lines'
LINE_STRIP='line_strip'

declare -A RENDER_TYPES=(
    ['venthyr_widow_bloom']="$CUTOUT"
    ['wall_light']="$CUTOUT"
    ['wardrobe_bottom']="$CUTOUT"
    ['wardrobe_top']="$CUTOUT"
    ['wool']="$CUTOUT"
    ['berry_basket_blueberry']="$CUTOUT"
    ['berry_basket_empty']="$CUTOUT"
    ['berry_basket_strawberry']="$CUTOUT"
    ['berry_basket_sweetberry']="$CUTOUT"
    ['bolts_of_cloth']="$CUTOUT"
    ['bone_candles']="$CUTOUT"
    ['bone_skeleton_chalices']="$CUTOUT"
    ['bone_skeleton_pile']="$CUTOUT"
    ['bone_skeleton_skull_blossoms']="$CUTOUT"
    ['bone_wither_chalices']="$CUTOUT"
    ['bone_wither_pile']="$CUTOUT"
    ['bone_wither_skull_blossoms']="$CUTOUT"
    ['book_stack']="$CUTOUT"
    ['bookshelf']="$CUTOUT"
    ['bowl_beetroot_soup']="$CUTOUT"
    ['bowl_empty']="$CUTOUT"
    ['bowl_mushroom_stew']="$CUTOUT"
    ['carpet']="$CUTOUT"
    ['chair']="$CUTOUT"
    ['chandelier']="$CUTOUT"
    ['chest']="$CUTOUT"
    ['coin_stack_gold']="$CUTOUT"
    ['coin_stack_iron']="$CUTOUT"
    ['cushion']="$CUTOUT"
    ['desk_left']="$CUTOUT"
    ['desk_right']="$CUTOUT"
    ['door_double']="$CUTOUT"
    ['door_single']="$CUTOUT"
    ['dresser']="$CUTOUT"
    ['dunmer_pottery_0']="$CUTOUT"
    ['dunmer_pottery_1']="$CUTOUT"
    ['floor_light']="$CUTOUT"
    ['furniture_station']="$CUTOUT"
    ['lockbox']="$CUTOUT"
    ['muffins_blueberry']="$CUTOUT"
    ['muffins_chocolate']="$CUTOUT"
    ['muffins_sweetberry']="$CUTOUT"
    ['mushrooms_red']="$CUTOUT"
    ['nordic_boiled_creme_treats']="$CUTOUT"
    ['nordic_mead_bottles']="$CUTOUT"
    ['nordic_soul_gems_dark']="$CUTOUT"
    ['nordic_soul_gems_light']="$CUTOUT"
    ['nordic_sweetrolls']="$CUTOUT"
    ['painting_small']="$CUTOUT"
    ['painting_wide']="$CUTOUT"
    ['paper_stack']="$CUTOUT"
    ['skull']="$CUTOUT"
    ['sofa']="$CUTOUT"
    ['stool']="$CUTOUT"
    ['table_large']="$CUTOUT"
    ['table_large_fancy']="$CUTOUT"
    ['table_small']="$CUTOUT"
    ['table_small_fancy']="$CUTOUT"
    ['table_wide']="$CUTOUT"
    ['table_wide_fancy']="$CUTOUT"
    ['tankards_empty']="$CUTOUT"
    ['tankards_honeymead']="$CUTOUT"
    ['tankards_milk']="$CUTOUT"
    ['tankards_sweetberry']="$CUTOUT"
    ['venthyr_candles']="$CUTOUT"
    ['venthyr_chalices']="$CUTOUT"
    ['venthyr_food_0']="$CUTOUT"
    ['venthyr_food_1']="$CUTOUT"
    ['venthyr_platter']="$CUTOUT"
    ['venthyr_tea_cups']="$CUTOUT"
    ['venthyr_tea_set']="$CUTOUT"
    ['venthyr_tomes']="$CUTOUT"
)

INPUT_DIRS="src/generated/resources/${MODELS_PATH} src/main/resources/${MODELS_PATH} test/main/resources/${MODELS_PATH}"

for dir in $(echo "$INPUT_DIRS"); do
    if [[ -d "$dir" ]]; then
        for file in $(find "${dir}" -name *json); do
            fileName="${file%.*}" 
            fileName=$(echo $fileName | rev | cut -d/ -f1 | rev)
            if [[ ${RENDER_TYPES[$fileName]+true} ]]; then
                
                found=$(jq '.render_type' $file)
                if [[ "$found" == "null" ]]; then
                    echo "Processing Model: '${file}'..."
                    renderType=${RENDER_TYPES[$fileName]}
                    echo "$(cat $file | jq -r --arg renderType "$renderType" '.render_type = $renderType')" > $file
                else
                    echo "Skipping: '${file}'..."
                fi
            fi
        done
    fi
done