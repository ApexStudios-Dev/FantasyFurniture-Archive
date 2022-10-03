Stream.of(
Block.box(2, 0, 2, 5, 2, 5),
Block.box(2, 0, 11, 5, 2, 14),
Block.box(11, 0, 11, 14, 2, 14),
Block.box(11, 0, 2, 14, 2, 5),
Block.box(11, 2, 3, 13, 3, 5),
Block.box(3, 2, 3, 5, 3, 5),
Block.box(3, 2, 11, 5, 3, 13),
Block.box(11, 2, 11, 13, 3, 13),
Block.box(2, 3, 2, 14, 4, 14),
Block.box(2.5, 4, 2.5, 13.5, 7, 13.5)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();