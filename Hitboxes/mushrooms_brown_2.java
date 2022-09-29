Stream.of(
Block.box(9, 0, 3, 11, 1, 5),
Block.box(8, 1, 2, 12, 3, 6),
Block.box(2, 2, 7, 6, 4, 11),
Block.box(3, 0, 8, 5, 2, 10),
Block.box(11, 0, 11, 13, 4, 13),
Block.box(9, 4, 9, 15, 6, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();