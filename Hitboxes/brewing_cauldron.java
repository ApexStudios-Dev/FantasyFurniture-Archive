Stream.of(
Block.box(3, 0, 3, 5, 2, 5),
Block.box(3, 0, 11, 5, 2, 13),
Block.box(11, 0, 11, 13, 2, 13),
Block.box(11, 0, 3, 13, 2, 5),
Block.box(2, 2, 2, 14, 9, 14),
Block.box(1, 9, 1, 15, 11, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();